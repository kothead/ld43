package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class AttachedToComponent implements Component {

    public static final ComponentMapper<AttachedToComponent> mapper = ComponentMapper.getFor(AttachedToComponent.class);

    public Entity parent;
    public Vector2 offset;

    public AttachedToComponent(Entity parent, float offsetx, float offsety) {
        this.parent = parent;
        offset = new Vector2(offsetx, offsety);
    }
}
