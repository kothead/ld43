package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector3;

public class HitscanComponent implements Component {

    public static final ComponentMapper<HitscanComponent> mapper = ComponentMapper.getFor(HitscanComponent.class);

    public enum Type {
        RAY,
        BEAM
    }

    public final Type type;
    public final Vector3 origin;
    public float power;

    public int exp;
    public int level = 1;

    public HitscanComponent(Type type, Vector3 origin, float power) {
        this.type = type;
        this.origin = origin;
        this.power = power;
    }
}
