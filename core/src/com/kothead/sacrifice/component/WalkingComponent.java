package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class WalkingComponent implements Component {

    public static final ComponentMapper<WalkingComponent> mapper = ComponentMapper.getFor(WalkingComponent.class);
}
