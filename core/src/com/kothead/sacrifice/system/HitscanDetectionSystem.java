package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.math.Vector2;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.component.*;
import com.kothead.gdxjam.base.system.CollisionDetectionSystem;
import com.kothead.sacrifice.Assets;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.component.*;
import com.kothead.sacrifice.state.JoeState;
import com.kothead.sacrifice.util.AnimationHelper;

public class HitscanDetectionSystem extends CollisionDetectionSystem {

    public HitscanDetectionSystem(int priority) {
        super(priority,
                Family.all(
                        HitscanComponent.class,
                        CollisionBoxComponent.class
                ).get(),
                Family.all(
                        VictimComponent.class,
                        CollisionBoxComponent.class
                ).get());
    }

    @Override
    protected void onCollisionDetected(Entity first, Entity second) {
        HitscanComponent hitscan = HitscanComponent.mapper.get(first);
        float power = hitscan.power * hitscan.level;

        if (!FlyingComponent.mapper.has(second)) {

            if (StateMachineComponent.mapper.has(second)) {
                StateMachine machine = StateMachineComponent.mapper.get(second).stateMachine;
                if (machine.getCurrentState() instanceof JoeState) {
                    JoeState state = (JoeState) machine.getCurrentState();
                    switch (hitscan.type) {
                        case RAY:
                            HealthComponent health = HealthComponent.mapper.get(second);
                            health.healthPoints -= power * Gdx.graphics.getDeltaTime();

                            if (health.healthPoints < 0 && state != JoeState.DIE) {
                                machine.changeState(JoeState.DIE);
                                GdxJam.assets().get(Assets.sounds.BURN).play();
                            }
                            break;

                        case BEAM:
                            if (state != JoeState.DIE) {
                                Vector2 velocity = VelocityComponent.mapper.get(second).velocity;
                                velocity.y = power * EntityManager.BEAM_SCALE_UP;
                                velocity.x = getCenterXFor(first) > getCenterXFor(second) ? power : -power;

                                if (state != JoeState.TAKEN) {
                                    machine.changeState(JoeState.TAKEN);
                                }
                            }
                            break;
                    }
                }
            } else {
                // WINGS CRUTCH
                if (hitscan.type == HitscanComponent.Type.RAY) {
                    AnimationHelper.setAnimation(second, Assets.animations.WING_BURN);
                    second.add(new DeleteAfterTimeComponent(GdxJam.assets().get(Assets.animations.WING_BURN).getAnimationDuration()));
                    second.remove(VictimComponent.class);
                }
            }
        }
    }

    private float getCenterXFor(Entity entity) {
        return SpriteComponent.mapper.get(entity).sprite.getWidth() / 2.0f
                + PositionComponent.mapper.get(entity).position.x;
    }
}
