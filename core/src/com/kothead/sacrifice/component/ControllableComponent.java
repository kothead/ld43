package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class ControllableComponent implements Component {

    public static final ComponentMapper<ControllableComponent> mapper = ComponentMapper.getFor(ControllableComponent.class);
}
