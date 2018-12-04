package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class AltarComponent implements Component {

    public static final ComponentMapper<AltarComponent> mapper = ComponentMapper.getFor(AltarComponent.class);

    public final Entity hitscan;

    public AltarComponent(Entity hitscan) {
        this.hitscan = hitscan;
    }
}
