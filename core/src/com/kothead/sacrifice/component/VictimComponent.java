package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class VictimComponent implements Component {

    public static final ComponentMapper<VictimComponent> mapper = ComponentMapper.getFor(VictimComponent.class);

    public float timeFromDecision = 0.0f;
}
