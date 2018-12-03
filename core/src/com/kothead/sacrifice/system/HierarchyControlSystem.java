package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.sacrifice.component.AttachedToComponent;

public class HierarchyControlSystem extends IteratingSystem {

    public HierarchyControlSystem(int priority) {
        super(Family.all(
                AttachedToComponent.class,
                PositionComponent.class
        ).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AttachedToComponent attachedTo = AttachedToComponent.mapper.get(entity);
        Vector3 childPosition = PositionComponent.mapper.get(entity).position;
        Vector3 parentPosition = PositionComponent.mapper.get(attachedTo.parent).position;

        childPosition.set(
                parentPosition.x + attachedTo.offset.x,
                parentPosition.y + attachedTo.offset.y,
                0
        );
    }
}
