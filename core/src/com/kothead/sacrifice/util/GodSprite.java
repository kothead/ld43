package com.kothead.sacrifice.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class GodSprite extends Sprite {

    private static final int TAIL_LAYERS_NUMBER = 5;
    private static final int POSITION_RAND_OFFSET = 5;

    private static final float DELTA_POSITION_UPDATE = 0.05f;
    private static final float FOG_RANGE = 0.4f;

    private float timeToUpdate = 0;
    private LinkedList<Vector2> queue = new LinkedList<Vector2>();

    @Override
    public void setPosition(float x, float y) {
        if (timeToUpdate > DELTA_POSITION_UPDATE) {
            if (queue.size() >= TAIL_LAYERS_NUMBER) {
                queue.pop();
            }

            for (int i = queue.size(); i < TAIL_LAYERS_NUMBER; i++) {
                queue.addLast(new Vector2(getX(), getY()));
            }
            timeToUpdate = 0;
        }

        super.setPosition(x, y);
    }

    @Override
    public void draw(Batch batch) {
        timeToUpdate += Gdx.graphics.getDeltaTime();

        float x = getX();
        float y = getY();

        float tint = 1.0f - FOG_RANGE;
        float delta = FOG_RANGE / TAIL_LAYERS_NUMBER;

        for (Vector2 position: queue) {
            setX((int) (position.x + (Math.random() - 0.5f) * POSITION_RAND_OFFSET));
            setY((int) (position.y + (Math.random() - 0.5f) * POSITION_RAND_OFFSET));
            setColor(tint, tint, tint, tint);
            super.draw(batch);

            tint += delta;
        }

        setX(x);
        setY(y);
        setColor(Color.WHITE);
        super.draw(batch);
    }
}
