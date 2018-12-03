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

    public HitscanComponent(Type type, Vector3 origin) {
        this.type = type;
        this.origin = origin;
    }
}
