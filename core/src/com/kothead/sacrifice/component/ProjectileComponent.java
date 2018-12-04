package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class ProjectileComponent implements Component {

    public static final ComponentMapper<ProjectileComponent> mapper = ComponentMapper.getFor(ProjectileComponent.class);

    public float damage;

    public ProjectileComponent(float damage) {
        this.damage = damage;
    }
}
