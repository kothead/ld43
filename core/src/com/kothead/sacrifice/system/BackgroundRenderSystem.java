package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.sacrifice.Assets;
import com.kothead.sacrifice.util.AnimationHelper;
import com.kothead.sacrifice.util.TiledForestSprite;

import java.util.ArrayList;
import java.util.List;

public class BackgroundRenderSystem extends EntitySystem {

    private static final float FOREST_DARKNESS_RANGE = 0.6f;

    private static final int FOREST_LAYERS_NUMBER = 5;
    private static final int GROUND_LEVEL = 100;

    private BaseScreen screen;
    private List<TiledForestSprite> background = new ArrayList<TiledForestSprite>();
    private Sprite moon;
    private float[] offsets = new float[FOREST_LAYERS_NUMBER];

    public BackgroundRenderSystem(int priority, BaseScreen screen) {
        super(priority);
        this.screen = screen;

        float size = 1.0f - FOREST_DARKNESS_RANGE;
        float delta = FOREST_DARKNESS_RANGE / FOREST_LAYERS_NUMBER;
        for (int i = 0; i < FOREST_LAYERS_NUMBER; i++) {
            TiledForestSprite sprite = new TiledForestSprite(GdxJam.assets().get(Assets.images.BACKGROUND),
                    screen.getWorldWidth(),
                    screen.getWorldHeight());
            background.add(sprite);

            offsets[i] = (float) Math.random() * screen.getWorldWidth();
            sprite.scale(size);
            size += delta;
        }

        TextureRegion moonTexture = GdxJam.assets().get(Assets.images.MOON);
        moon = new Sprite(moonTexture);
    }

    @Override
    public void update(float deltaTime) {
        screen.batch().begin();

        Vector3 focus = screen.getCamera().position;

        moon.setPosition(
                focus.x * 0.95f + 0.75f * moon.getWidth(),
                focus.y * 0.95f + 0.75f * moon.getHeight()
        );

        moon.draw(screen.batch());

        float offset = 0.75f - FOREST_DARKNESS_RANGE;
        float tint = 1.0f - FOREST_DARKNESS_RANGE;
        float delta = FOREST_DARKNESS_RANGE / FOREST_LAYERS_NUMBER;

        for (int i = 0; i < background.size(); i++) {
            TiledForestSprite sprite = background.get(i);
            sprite.setPosition(focus.x - screen.getWorldWidth() / 2f + offsets[i],
                    focus.y - screen.getWorldHeight() / 2f + GROUND_LEVEL);
            sprite.setOffset(-focus.x * offset, -focus.y * offset);
            sprite.setColor(tint - delta, tint - delta, tint - delta, 1);
            sprite.draw(screen.batch());

            tint += delta;
            offset += delta;
        }

        screen.batch().end();
    }
}
