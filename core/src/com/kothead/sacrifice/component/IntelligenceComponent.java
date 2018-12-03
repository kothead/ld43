package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class IntelligenceComponent implements Component {

    public static final ComponentMapper<IntelligenceComponent> mapper = ComponentMapper.getFor(IntelligenceComponent.class);

    public float timeFromDecision = 0.0f;
}
