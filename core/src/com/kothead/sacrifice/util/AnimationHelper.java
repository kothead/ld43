package com.kothead.sacrifice.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.component.AnimationComponent;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.gdxjam.base.util.TextureAnimation;

public class AnimationHelper {

    public static void setAnimation(Entity entity, AssetDescriptor<TextureAnimation> descriptor) {
        AnimationComponent component = AnimationComponent.mapper.get(entity);
        if (component == null) {
            component = new AnimationComponent(GdxJam.assets().get(descriptor));
            entity.add(component);
        } else {
            component.animation = GdxJam.assets().get(descriptor);
            component.time = 0f;
        }

        // so we can change sprite instantly after setting animation for the first time
        setTexture(entity, component.animation.getKeyFrame(0));
    }

    public static void setTexture(Entity entity, AssetDescriptor<TextureRegion> descriptor) {
        entity.remove(AnimationComponent.class);
        setTexture(entity, GdxJam.assets().get(descriptor));
    }

    public static void setTexture(Entity entity, TextureRegion region) {
        SpriteComponent component = SpriteComponent.mapper.get(entity);
        if (component == null) {
            component = new SpriteComponent(new Sprite(region));
            entity.add(component);
        } else {
            Sprite sprite = component.sprite;
            boolean flipX = sprite.isFlipX();
            boolean flipY = sprite.isFlipY();

            sprite.setRegion(region);
            sprite.setSize(region.getRegionWidth(), region.getRegionHeight());
            sprite.setFlip(flipX, flipY);
        }
    }
}
