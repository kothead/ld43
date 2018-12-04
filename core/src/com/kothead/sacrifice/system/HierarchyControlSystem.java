package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.sacrifice.component.AttachedToComponent;

public class HierarchyControlSystem extends IteratingSystem {

    public HierarchyControlSystem(int priority) {
        super(Family.all(
                AttachedToComponent.class,
                PositionComponent.class,
                SpriteComponent.class
        ).get(), priority);
    }

    @Override
    protected void processEntity(Entity child, float deltaTime) {
        AttachedToComponent attachedTo = AttachedToComponent.mapper.get(child);
        Entity parent = attachedTo.parent;

        Vector3 childPosition = PositionComponent.mapper.get(child).position;
        Vector3 parentPosition = PositionComponent.mapper.get(parent).position;

        childPosition.set(
                parentPosition.x + attachedTo.offset.x,
                parentPosition.y + attachedTo.offset.y,
                0
        );

        Sprite childSprite = SpriteComponent.mapper.get(child).sprite;
        Sprite parentSprite = SpriteComponent.mapper.get(parent).sprite;

        // CRUTCH
        if (parentSprite.isFlipX() != childSprite.isFlipX()) {
            attachedTo.offset.x += parentSprite.isFlipX() ? parentSprite.getWidth() : -parentSprite.getWidth();
        }

        childSprite.flip(
                parentSprite.isFlipX() != childSprite.isFlipX(),
                parentSprite.isFlipY() != childSprite.isFlipY()
        );
    }
}
