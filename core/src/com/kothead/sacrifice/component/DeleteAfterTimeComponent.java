package com.kothead.sacrifice.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class DeleteAfterTimeComponent implements Component {

    public static final ComponentMapper<DeleteAfterTimeComponent> mapper = ComponentMapper.getFor(DeleteAfterTimeComponent.class);

    public float timeToDelete = 0.0f;
    public float timePassed = 0.0f;

    public DeleteAfterTimeComponent(float timeToDelete) {
        this.timeToDelete = timeToDelete;
    }
}
