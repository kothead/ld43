package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.math.Vector2;
import com.kothead.gdxjam.base.component.*;
import com.kothead.gdxjam.base.system.CollisionDetectionSystem;
import com.kothead.sacrifice.component.HitscanComponent;
import com.kothead.sacrifice.component.VictimComponent;
import com.kothead.sacrifice.state.JoeState;

public class HitscanDetectionSystem extends CollisionDetectionSystem {

    private static final float STARTING_POWER_BEAM_UP = 20.0f;
    private static final float STARTING_POWER_BEAM_DRAG = 5.0f;

    private float powerBeamUp = STARTING_POWER_BEAM_UP;
    private float powerBeamDrag = STARTING_POWER_BEAM_DRAG;

    public HitscanDetectionSystem(int priority) {
        super(priority,
                Family.all(
                        HitscanComponent.class,
                        CollisionBoxComponent.class
                ).get(),
                Family.all(
                        VictimComponent.class,
                        CollisionBoxComponent.class,
                        StateMachineComponent.class
                ).get());
    }

    @Override
    protected void onCollisionDetected(Entity first, Entity second) {
        HitscanComponent.Type type = HitscanComponent.mapper.get(first).type;
        StateMachine machine = StateMachineComponent.mapper.get(second).stateMachine;

        if (machine.getCurrentState() instanceof JoeState) {
            JoeState state = (JoeState) machine.getCurrentState();
            switch (type) {
                case RAY:
                    if (state != JoeState.DIE) {
                        machine.changeState(JoeState.DIE);
                    }
                    break;

                case BEAM:
                    if (state != JoeState.DIE) {
                        Vector2 velocity = VelocityComponent.mapper.get(second).velocity;
                        velocity.y = powerBeamUp;
                        velocity.x = getCenterXFor(first) > getCenterXFor(second) ? powerBeamDrag : -powerBeamDrag;

                        if (state != JoeState.TAKEN) {
                            machine.changeState(JoeState.TAKEN);
                        }
                    }
                    break;
            }
        }
    }

    private float getCenterXFor(Entity entity) {
        return SpriteComponent.mapper.get(entity).sprite.getWidth() / 2.0f
                + PositionComponent.mapper.get(entity).position.x;
    }
}
