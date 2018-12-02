package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class OrientationComponent implements Component {

    public static final ComponentMapper<OrientationComponent> mapper =  ComponentMapper.getFor(OrientationComponent.class);

    public enum Orientation {
        LEFT, RIGHT
    }

    public final Orientation orientation;

    public OrientationComponent(Orientation orientation) {
        this.orientation = orientation;
    }
}
