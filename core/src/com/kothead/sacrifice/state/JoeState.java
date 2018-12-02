package com.kothead.sacrifice.state;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.gdxjam.base.component.VelocityComponent;
import com.kothead.sacrifice.Assets;

import static com.kothead.sacrifice.Assets.animations.*;
import static com.kothead.sacrifice.util.AnimationHelper.setAnimation;
import static com.kothead.sacrifice.util.AnimationHelper.setTexture;

public enum JoeState implements State<Entity> {

    IDLE {
        @Override
        public void enter(Entity entity) {
            super.enter(entity);
            setTexture(entity, Assets.images.JOE_WALK_1);
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
}
