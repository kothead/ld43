package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.sacrifice.component.DeleteAfterTimeComponent;

public class CleanerSystem extends IteratingSystem {

    public CleanerSystem(int priority) {
        super(Family.all(DeleteAfterTimeComponent.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DeleteAfterTimeComponent component = DeleteAfterTimeComponent.mapper.get(entity);
        component.timePassed += deltaTime;

        if (component.timePassed > component.timeToDelete) {
            GdxJam.engine().removeEntity(entity);
        }
    }
}
