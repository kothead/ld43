package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.CollisionBoxComponent;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.sacrifice.component.HitscanComponent;

public class HitscanTracerSystem extends IteratingSystem {

    private static final int X_OFFSET = 24;
    private static final int Y_OFFSET = 32;

    private int levelGround;

    public HitscanTracerSystem(int priority, int levelGround) {
        super(Family.all(
                HitscanComponent.class,
                PositionComponent.class,
                CollisionBoxComponent.class
        ).get(), priority);
        this.levelGround = levelGround;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HitscanComponent hitscan = HitscanComponent.mapper.get(entity);

        Vector3 position = PositionComponent.mapper.get(entity).position;
        position.set(hitscan.origin.x + X_OFFSET, levelGround, 0);

        Sprite sprite = SpriteComponent.mapper.get(entity).sprite;
        sprite.setSize(sprite.getWidth(), hitscan.origin.y - levelGround + Y_OFFSET);

        CollisionBoxComponent.mapper.get(entity).collisionBox.setVertices(new float[] {
                0, 0,
                sprite.getWidth(), 0,
                sprite.getWidth(), sprite.getHeight(),
                0, sprite.getHeight()
        });
    }
}
