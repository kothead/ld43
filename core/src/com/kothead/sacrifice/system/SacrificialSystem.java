package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.component.CollisionBoxComponent;
import com.kothead.gdxjam.base.component.StateMachineComponent;
import com.kothead.gdxjam.base.system.CollisionDetectionSystem;
import com.kothead.sacrifice.Assets;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.component.AltarComponent;
import com.kothead.sacrifice.component.HealthComponent;
import com.kothead.sacrifice.component.HitscanComponent;
import com.kothead.sacrifice.component.VictimComponent;
import com.kothead.sacrifice.state.JoeState;

public class SacrificialSystem extends CollisionDetectionSystem {

    private ImmutableArray<Entity> playerParts;

    public SacrificialSystem(int priority) {
        super(priority,
                Family.all(
                        AltarComponent.class,
                        CollisionBoxComponent.class
                ).get(),
                Family.all(
                        VictimComponent.class,
                        CollisionBoxComponent.class,
                        StateMachineComponent.class
                ).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        playerParts = engine.getEntitiesFor(Family.all(HealthComponent.class).get());
    }

    @Override
    protected void onCollisionDetected(Entity first, Entity second) {
        JoeState state = (JoeState) StateMachineComponent.mapper.get(second).stateMachine.getCurrentState();

        HealthComponent health = HealthComponent.mapper.get(playerParts.first());

        if (state == JoeState.DIE) {
            second.remove(VictimComponent.class);
            health.healthPoints += EntityManager.RESTORE_HP_PER_SACRIFICE;
            if (health.healthPoints > EntityManager.GOD_HIT_POINTS) health.healthPoints = EntityManager.GOD_HIT_POINTS;

            HitscanComponent hitscan = HitscanComponent.mapper.get(AltarComponent.mapper.get(first).hitscan);
            hitscan.exp += EntityManager.EXP_FOR_SACRIFICE;
            if (hitscan.exp >= EntityManager.EXP_FOR_LEVEL + EntityManager.EXP_LEVEL_INCREASE * hitscan.level) {
                hitscan.level++;
                hitscan.exp = 0;
                GdxJam.assets().get(Assets.sounds.LEVEL).play();
            } else {
                GdxJam.assets().get(Assets.sounds.SACRIFICE).play();
            }
        }
    }
}
