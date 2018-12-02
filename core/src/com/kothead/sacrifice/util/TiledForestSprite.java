package com.kothead.sacrifice.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TiledForestSprite extends Sprite {

    private Vector2 offset = new Vector2(0.0f, 0.0f);

    public TiledForestSprite(TextureRegion region) {
        super(region);
    }

    public TiledForestSprite(TextureRegion region, float width, float height) {
        this(region);
        setSize(width, height);
    }

    @Override
    public void draw(Batch batch) {
        float regionWidth = getRegionWidth() * getScaleX();
        float regiondHeight = getRegionHeight() * getScaleY();

        float x = offset.x % regionWidth;

        batch.setColor(getColor());
        for (float i = getX() + x; i < getX() + getWidth(); i += regionWidth) {
            // V2 and V1 should be reversed, because the coordinate system of textures
            // rendering in a sprite batch is from bottom to top, but the system
            // which is used in a texture region is from top to bottom
            batch.draw(getTexture(), i, getY() + offset.y, regionWidth, regiondHeight,
                    getU(), getV2(), getU2(), getV());
        }
        batch.setColor(Color.WHITE);
    }

    public void offset(Vector2 offset) {
        offset(offset.x, offset.y);
    }

    public void offset(float x, float y) {
        offset.add(x, y);
        cutExcess();
    }

    public void setOffset(Vector2 offset) {
        setOffset(offset.x, offset.y);
    }

    public void setOffset(float x, float y) {
        offset.set(x, y);
        cutExcess();
    }

    private void cutExcess() {
        float regionWidth = getRegionWidth() * getScaleX();
        if (Math.abs(offset.x) > regionWidth) offset.x -= Math.copySign(regionWidth, offset.x);
    }
}
