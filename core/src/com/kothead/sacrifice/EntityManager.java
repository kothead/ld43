package com.kothead.sacrifice;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.kothead.gdxjam.base.component.*;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.gdxjam.base.system.*;
import com.kothead.gdxjam.base.util.EntityStateMachine;
import com.kothead.sacrifice.component.ControllableComponent;
import com.kothead.sacrifice.component.OrientationComponent;
import com.kothead.sacrifice.component.VictimComponent;
import com.kothead.sacrifice.state.JoeState;
import com.kothead.sacrifice.system.*;
import com.kothead.sacrifice.system.CameraControlSystem;
import com.kothead.sacrifice.util.AnimationHelper;
import com.kothead.sacrifice.util.GodSprite;

public class EntityManager {

    private static final int LEVEL_WIDTH = 800;
    private static final int LEVEL_HEIGHT = 1600;

    private Engine engine;
    private BaseScreen screen;

    public EntityManager(Engine engine, BaseScreen screen) {
        this.engine = engine;
        this.screen = screen;
        registerSystems();
    }

    public void registerSystems() {
        int priority = 0;
        engine.addSystem(new InputSystem(priority++, screen));
        engine.addSystem(new VictimSystem(priority++, LEVEL_WIDTH, LEVEL_HEIGHT));
        engine.addSystem(new GravitySystem(priority++));
        engine.addSystem(new MovementSystem(priority++));
        engine.addSystem(new CameraControlSystem(priority++, screen, LEVEL_WIDTH, LEVEL_HEIGHT));
        engine.addSystem(new StateMachineSystem(priority++));
        engine.addSystem(new AnimationSystem(priority++));
        engine.addSystem(new BackgroundRenderSystem(priority++, screen));
        engine.addSystem(new RenderSystem(priority++, screen.batch()));
        engine.addSystem(new DebugRenderSystem(priority++, screen));
    }

    public Entity addPlayerHead() {
        Entity entity = new Entity();
//        AnimationHelper.setTexture(entity, Assets.images.MASK1);
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setAnimation(entity, Assets.animations.GOD_FACE);

        entity.add(new PositionComponent(0.0f, 0.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());
//        entity.add(new DebugComponent(new Polygon(new float[] {
//                0, 0,
//                64, 0,
//                64, 64,
//                0, 64
//        })));

        engine.addEntity(entity);
        return entity;
    }

    public Entity addPlayerLeftHand() {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.HAND_RIGHT);

        entity.add(new OrientationComponent(OrientationComponent.Orientation.LEFT));
        entity.add(new PositionComponent(0.0f, 0.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());

        engine.addEntity(entity);
        return entity;
    }

    public Entity addJoe() {
        Entity entity = new Entity();

        entity.add(new SpriteComponent(new Sprite()));
        entity.add(new PositionComponent(0.0f, 32.0f, 0.0f));
        entity.add(new VelocityComponent(30.0f, 0.0f));
        entity.add(new StateMachineComponent(new EntityStateMachine(entity, JoeState.WALK)));
        entity.add(new VictimComponent());
        engine.addEntity(entity);
        return entity;
    }

    public Entity addPlayerRightHand() {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.HAND_LEFT);

        entity.add(new OrientationComponent(OrientationComponent.Orientation.RIGHT));
        entity.add(new PositionComponent(0.0f, 0.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());

        engine.addEntity(entity);
        return entity;
    }

    public void dispose() {
        engine.removeAllEntities();
        engine.removeAllSystems();
    }
}
