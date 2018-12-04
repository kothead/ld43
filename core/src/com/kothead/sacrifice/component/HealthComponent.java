package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class HealthComponent implements Component {

    public static final ComponentMapper<HealthComponent> mapper = ComponentMapper.getFor(HealthComponent.class);

    public float healthPoints;

    public HealthComponent(float healthPoints) {
        this.healthPoints = healthPoints;
    }
}
