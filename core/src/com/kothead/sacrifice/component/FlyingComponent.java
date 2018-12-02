package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class FlyingComponent implements Component {

    public static final ComponentMapper<FlyingComponent> mapper = ComponentMapper.getFor(FlyingComponent.class);
}
