package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.kothead.gdxjam.base.component.VelocityComponent;
import com.kothead.sacrifice.component.GravityComponent;

public class GravitySystem extends IteratingSystem {

    public GravitySystem(int priority) {
        super(Family.all(VelocityComponent.class, GravityComponent.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector2 velocity = VelocityComponent.mapper.get(entity).velocity;

        GravityComponent gravityComponent = GravityComponent.mapper.get(entity);
        Vector2 gravity = gravityComponent.gravity;

        velocity.mulAdd(gravity, deltaTime);
    }
}
