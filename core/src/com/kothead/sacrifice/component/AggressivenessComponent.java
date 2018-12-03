package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class AggressivenessComponent implements Component {

    public static final ComponentMapper<AggressivenessComponent> mapper = ComponentMapper.getFor(AggressivenessComponent.class);

    public float aggressiveness = 0.0f;

    public AggressivenessComponent(float aggressiveness) {
        this.aggressiveness = aggressiveness;
    }
}
