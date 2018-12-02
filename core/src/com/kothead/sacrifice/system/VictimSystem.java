package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.gdxjam.base.component.VelocityComponent;
import com.kothead.sacrifice.component.VictimComponent;

public class VictimSystem extends IteratingSystem {

    private static final float SPEED_WALK = 30.0f;
    private static final float SPEED_FLY = 40.0f;
    private static final float TIME_TO_DECIDE = 1.0f;

    private static final int DISTANCE_FROM_BORDER = 16;

    private int levelWidth;
    private int levelHeight;

    public VictimSystem(int priority, int levelWidth, int levelHeight) {
        super(Family.all(VelocityComponent.class, PositionComponent.class, VictimComponent.class).get(), priority);

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VictimComponent victimComponent = VictimComponent.mapper.get(entity);
        victimComponent.timeFromDecision += deltaTime;

        Vector2 velocity = VelocityComponent.mapper.get(entity).velocity;
        Vector3 position = PositionComponent.mapper.get(entity).position;

        if (victimComponent.timeFromDecision > TIME_TO_DECIDE) {
            victimComponent.timeFromDecision = 0;

            velocity.x = Math.random() > 0.5f ? SPEED_WALK : -SPEED_WALK;
        }

        if (position.x < DISTANCE_FROM_BORDER && velocity.x < 0) {
            velocity.x = SPEED_WALK;
        }

        if (position.x > levelWidth - DISTANCE_FROM_BORDER && velocity.x > 0) {
            velocity.x = -SPEED_WALK;
        }
    }
}
