package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.component.StateMachineComponent;
import com.kothead.sacrifice.component.AttachedToComponent;
import com.kothead.sacrifice.component.DeleteAfterTimeComponent;
import com.kothead.sacrifice.component.GravityComponent;
import com.kothead.sacrifice.component.FlyingComponent;
import com.kothead.sacrifice.state.JoeState;

public class CleanerSystem extends IteratingSystem {

    public CleanerSystem(int priority) {
        super(Family.all(DeleteAfterTimeComponent.class).get(), priority);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DeleteAfterTimeComponent component = DeleteAfterTimeComponent.mapper.get(entity);
        component.timePassed += deltaTime;

        if (AttachedToComponent.mapper.has(entity)) {
            Entity parent = AttachedToComponent.mapper.get(entity).parent;

            if (FlyingComponent.mapper.has(parent)) {
                parent.remove(FlyingComponent.class);
            }

            if (StateMachineComponent.mapper.has(parent)) {
                StateMachineComponent.mapper.get(parent).stateMachine.changeState(JoeState.IDLE);
            }

            parent.add(new GravityComponent());
        }

        if (component.timePassed > component.timeToDelete) {
            GdxJam.engine().removeEntity(entity);
        }
    }
}
