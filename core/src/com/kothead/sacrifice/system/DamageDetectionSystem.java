package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.component.CollisionBoxComponent;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.gdxjam.base.component.VelocityComponent;
import com.kothead.gdxjam.base.system.CollisionDetectionSystem;
import com.kothead.sacrifice.Assets;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.GodGame;
import com.kothead.sacrifice.component.*;

public class DamageDetectionSystem extends CollisionDetectionSystem {

    private EntityManager manager;

    public DamageDetectionSystem(int priority, EntityManager manager) {
        super(priority,
                Family.all(
                        ProjectileComponent.class,
                        CollisionBoxComponent.class
                ).get(),
                Family.all(
                        ControllableComponent.class,
                        CollisionBoxComponent.class,
                        HealthComponent.class
                ).get());
        this.manager = manager;
    }

    @Override
    protected void onCollisionDetected(Entity first, Entity second) {
        // in case it has already hit something in this iteration
        if (!ProjectileComponent.mapper.has(first)) return;

        HealthComponent health = HealthComponent.mapper.get(second);
        health.healthPoints -= ProjectileComponent.mapper.get(first).damage;
        GdxJam.assets().get(Assets.sounds.HIT).play();
        if (health.healthPoints < 0) {
            manager.readyToGameOver();
        }

        PositionComponent firstPosition = PositionComponent.mapper.get(first);
        PositionComponent secondPosition = PositionComponent.mapper.get(second);

        first.remove(ProjectileComponent.class);
        first.remove(VelocityComponent.class);
        first.remove(GravityComponent.class);
        first.add(new AttachedToComponent(second,
                firstPosition.position.x - secondPosition.position.x,
                firstPosition.position.y - secondPosition.position.y));

        first.add(new DeleteAfterTimeComponent(EntityManager.DELETE_SPEAR_AFTER));
    }
}
