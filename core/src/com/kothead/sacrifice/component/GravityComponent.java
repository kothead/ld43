package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;

public class GravityComponent implements Component {

    public static final ComponentMapper<GravityComponent> mapper = ComponentMapper.getFor(GravityComponent.class);

    public static final Vector2 GRAVITY = new Vector2(0.0f, -50.0f);

    public Vector2 gravity;

    public GravityComponent() {
        this(GRAVITY);
    }

    public GravityComponent(float x, float y) {
        gravity = new Vector2(x, y);
    }

    public GravityComponent(Vector2 gravity) {
        this.gravity = new Vector2(gravity);
    }
}
