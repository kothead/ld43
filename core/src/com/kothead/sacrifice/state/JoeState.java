package com.kothead.sacrifice.state;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.component.AnimationComponent;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.gdxjam.base.component.StateMachineComponent;
import com.kothead.gdxjam.base.component.VelocityComponent;
import com.kothead.sacrifice.Assets;
import com.kothead.sacrifice.component.IntelligenceComponent;
import com.kothead.sacrifice.system.AiSystem;

import static com.kothead.sacrifice.Assets.animations.*;
import static com.kothead.sacrifice.util.AnimationHelper.setAnimation;
import static com.kothead.sacrifice.util.AnimationHelper.setTexture;

public enum JoeState implements State<Entity> {

    IDLE {
        @Override
        public void enter(Entity entity) {
            super.enter(entity);
            setTexture(entity, Assets.images.JOE_WALK_0);
        }
    },
    WALK {
        @Override
        public void enter(Entity entity) {
            super.enter(entity);
            setAnimation(entity, Assets.animations.JOE_WALK);
        }

        @Override
        public void update(Entity entity) {
            super.update(entity);

            Sprite sprite = SpriteComponent.mapper.get(entity).sprite;
            Vector2 velocity = VelocityComponent.mapper.get(entity).velocity;
            if (velocity.x > 0.0f && sprite.isFlipX()
                    || velocity.x < 0.0f && !sprite.isFlipX()) {
                sprite.flip(true, false);
            }
        }
    },
    DIE {
        @Override
        public void enter(Entity entity) {
            super.enter(entity);
            setAnimation(entity, Assets.animations.JOE_DIE);

            if (IntelligenceComponent.mapper.has(entity)) {
                entity.remove(IntelligenceComponent.class);
            }

            VelocityComponent.mapper.get(entity).velocity.x = 0.0f;
        }

        @Override
        public void update(Entity entity) {
            super.update(entity);

            if (AnimationComponent.mapper.get(entity).isAnimationFinished()) {
                GdxJam.engine().removeEntity(entity);
            }
        }

        @Override
        public void exit(Entity entity) {
            super.exit(entity);

            if (!IntelligenceComponent.mapper.has(entity)) {
                entity.add(new IntelligenceComponent());
            }
        }
    },
    TAKEN {
        @Override
        public void enter(Entity entity) {
            super.enter(entity);

            setTexture(entity, Assets.images.JOE_TAKEN);
        }

        @Override
        public void update(Entity entity) {
            super.update(entity);

            if (VelocityComponent.mapper.get(entity).velocity.y < 0) {
                JoeState.changeState(entity, WALK);
            }
        }
    },
    THROW {
        @Override
        public void enter(Entity entity) {
            super.enter(entity);

            setAnimation(entity, Assets.animations.JOE_THROW);
        }

        @Override
        public void update(Entity entity) {
            super.update(entity);

            if (AnimationComponent.mapper.get(entity).isAnimationFinished()) {
                IntelligenceComponent.mapper.get(entity).timeFromDecision = AiSystem.TIME_TO_DECIDE;
            }
        }
    };

    @Override
    public void enter(Entity entity) {

    }

    @Override
    public void update(Entity entity) {

    }

    @Override
    public void exit(Entity entity) {

    }

    @Override
    public boolean onMessage(Entity entity, Telegram telegram) {
        return false;
    }

    private static State getState(Entity entity) {
        if (!StateMachineComponent.mapper.has(entity)) return null;
        return StateMachineComponent.mapper.get(entity).stateMachine.getCurrentState();
    }

    private static void changeState(Entity entity, JoeState state) {
        if (!StateMachineComponent.mapper.has(entity)) return;
        StateMachineComponent.mapper.get(entity).stateMachine.changeState(state);
    }
}
