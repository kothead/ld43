package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.component.FlyingComponent;
import com.kothead.sacrifice.component.HitscanComponent;
import com.kothead.sacrifice.component.VictimComponent;

public class WaveGeneratorSystem extends EntitySystem {

    private EntityManager manager;
    private ImmutableArray<Entity> entities;
    private ImmutableArray<Entity> hitscans;

    private int previousWave = 1;

    public WaveGeneratorSystem(int priority, EntityManager manager) {
        super(priority);
        this.manager = manager;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        entities = engine.getEntitiesFor(Family.all(VictimComponent.class).get());
        hitscans = engine.getEntitiesFor(Family.all(HitscanComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        int wave = 0;
        for (Entity hitscan: hitscans) {
            wave += HitscanComponent.mapper.get(hitscan).level;
        }
        wave -= 2;

        previousWave = wave;

        int simpleJoesPerWave = EntityManager.STARTING_JOES_COUNT + wave * EntityManager.JOES_PER_WAVE_INCREASE;
        int flyingJoesPerWave = EntityManager.STARTING_FLYING_JOES_COUNT + wave * EntityManager.FLYING_JOES_PER_WAVE_INCREASE;

        int simpleJoesCount = 0;
        int flyingJoesCount = 0;
        for (Entity joe: entities) {
            if (FlyingComponent.mapper.has(joe)) {
                flyingJoesCount++;
            } else {
                simpleJoesCount++;
            }
        }

        if (flyingJoesCount < flyingJoesPerWave) {
            manager.addFlyingJoe();
        }

        if (simpleJoesCount < simpleJoesPerWave) {
            manager.addJoe();
        }

        if (wave >= EntityManager.LEVEL_WIN) {
            manager.readyToGameWin();
        }
    }
}
