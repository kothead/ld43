package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.gdxjam.base.component.VelocityComponent;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.component.DeleteAfterTimeComponent;
import com.kothead.sacrifice.component.GravityComponent;
import com.kothead.sacrifice.component.ProjectileComponent;

public class GravitySystem extends IteratingSystem {

    private int levelGround;

    public GravitySystem(int priority, int levelGround) {
        super(Family.all(
                PositionComponent.class,
                VelocityComponent.class,
                GravityComponent.class
        ).get(), priority);
        this.levelGround = levelGround;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Vector3 position = PositionComponent.mapper.get(entity).position;
        Vector2 velocity = VelocityComponent.mapper.get(entity).velocity;

        GravityComponent gravityComponent = GravityComponent.mapper.get(entity);
        Vector2 gravity = gravityComponent.gravity;

        velocity.mulAdd(gravity, deltaTime);

        if (position.y + velocity.y * deltaTime < levelGround) {
            position.y = levelGround;
            velocity.y = 0;

            if (ProjectileComponent.mapper.has(entity)
                    && !DeleteAfterTimeComponent.mapper.has(entity)) {
                velocity.x = 0;
                entity.add(new DeleteAfterTimeComponent(EntityManager.DELETE_SPEAR_AFTER));
            }
        } else if (ProjectileComponent.mapper.has(entity)) {
            Sprite sprite = SpriteComponent.mapper.get(entity).sprite;
            sprite.setOrigin(sprite.getWidth(), 0);
            sprite.setRotation(MathUtils.atan2(velocity.y, velocity.x) * MathUtils.radiansToDegrees);
        }
    }
}
