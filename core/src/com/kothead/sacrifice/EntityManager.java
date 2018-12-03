package com.kothead.sacrifice;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.*;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.gdxjam.base.system.*;
import com.kothead.gdxjam.base.util.EntityStateMachine;
import com.kothead.sacrifice.component.*;
import com.kothead.sacrifice.state.JoeState;
import com.kothead.sacrifice.system.*;
import com.kothead.sacrifice.system.CameraControlSystem;
import com.kothead.sacrifice.util.AnimationHelper;
import com.kothead.sacrifice.util.GodSprite;

public class EntityManager {

    private static final int LEVEL_WIDTH = 800;
    private static final int LEVEL_HEIGHT = 1600;
    private static final int LEVEL_GROUND = 32;

    private static final int LEFT_ALTAR_X = 250;
    private static final int RIGHT_ALTAR_X = 500;
    private static final int ALTAR_Y = 500;

    private static final float SPEED_SPEAR = 100.0f;

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
        engine.addSystem(new AiSystem(this, priority++, LEVEL_WIDTH, LEVEL_HEIGHT));
        engine.addSystem(new GravitySystem(priority++, LEVEL_GROUND));
        engine.addSystem(new MovementSystem(priority++));

        engine.addSystem(new HitscanTracerSystem(priority++, LEVEL_GROUND));
        engine.addSystem(new HitscanDetectionSystem(priority++));
        engine.addSystem(new CleanerSystem(priority++));

        engine.addSystem(new CameraControlSystem(priority++, screen, LEVEL_WIDTH, LEVEL_HEIGHT));
        engine.addSystem(new StateMachineSystem(priority++));
        engine.addSystem(new AnimationSystem(priority++));
        engine.addSystem(new HierarchyControlSystem(priority++));
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

    public Entity getPlayerLeftHand() {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.HAND_RIGHT);

        entity.add(new OrientationComponent(OrientationComponent.Orientation.LEFT));
        entity.add(new PositionComponent(0.0f, 0.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());

        // cannot add it right away, because of ray

        return entity;
    }

    public Entity getPlayerRightHand() {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.HAND_LEFT);

        entity.add(new OrientationComponent(OrientationComponent.Orientation.RIGHT));
        entity.add(new PositionComponent(0.0f, 0.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());

        // cannot add it right away, because of beam

        return entity;
    }

    public Entity addJoe() {
        Entity entity = new Entity();

        entity.add(new SpriteComponent(new Sprite()));
        entity.add(new PositionComponent(0.0f, 32.0f, 0.0f));
        entity.add(new VelocityComponent(30.0f, 0.0f));
        entity.add(new GravityComponent());
        entity.add(new StateMachineComponent(new EntityStateMachine(entity, JoeState.WALK)));
        entity.add(new IntelligenceComponent());
        entity.add(new VictimComponent());
        entity.add(new AggressivenessComponent(0.2f));

        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 0, 16, 0, 16, 32, 0, 32
        })));
        engine.addEntity(entity);
        return entity;
    }

    public Entity addFlyingJoe() {
        Entity entity = new Entity();

        entity.add(new SpriteComponent(new Sprite()));
        entity.add(new PositionComponent(0.0f, 32.0f, 0.0f));
        entity.add(new VelocityComponent(30.0f, 0.0f));
        entity.add(new StateMachineComponent(new EntityStateMachine(entity, JoeState.WALK)));
        entity.add(new IntelligenceComponent());
        entity.add(new VictimComponent());
        entity.add(new AggressivenessComponent(0.2f));

        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 0, 16, 0, 16, 32, 0, 32
        })));
        engine.addEntity(entity);

        Entity wings = new Entity();
        AnimationHelper.setAnimation(wings, Assets.animations.WING);
        wings.add(new PositionComponent(0, 0, 0));
        wings.add(new AttachedToComponent(entity, -16, 0));

        engine.addEntity(wings);

        return entity;
    }

    public Entity addSpear(Entity joe, Vector2 target) {
        Entity entity = new Entity();
        AnimationHelper.setTexture(entity, Assets.images.SPEAR);

        Vector3 joePosition = PositionComponent.mapper.get(joe).position;
        float dx = target.x - joePosition.x;
        float dy = target.y - joePosition.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        entity.add(new PositionComponent(joePosition));
        entity.add(new VelocityComponent(
                (float) (SPEED_SPEAR * dx / distance),
                (float) (SPEED_SPEAR * dy / distance)
        ));
        entity.add(new GravityComponent());
        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 0, 4, 0, 4, 4, 0, 4
        })));
        entity.add(new ProjectileComponent());
        engine.addEntity(entity);
        return entity;
    }

    public Entity addRay(Entity hand) {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.RAY);

        entity.add(new PositionComponent(0, 0, 0));
        entity.add(new HitscanComponent(
                HitscanComponent.Type.RAY,
                hand.getComponent(PositionComponent.class).position
        ));

        entity.add(new CollisionBoxComponent(new Polygon()));
        engine.addEntity(entity);
        return entity;
    }

    public Entity addBeam(Entity hand) {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.BEAM);

        entity.add(new PositionComponent(0, 0, 0));
        entity.add(new HitscanComponent(
                HitscanComponent.Type.BEAM,
                hand.getComponent(PositionComponent.class).position
        ));

        entity.add(new CollisionBoxComponent(new Polygon()));
        engine.addEntity(entity);
        return entity;
    }

    public Entity addAltarLeft() {
        Entity entity = new Entity();
        AnimationHelper.setAnimation(entity, Assets.animations.ALTAR_BLOODY);

        entity.add(new PositionComponent(LEFT_ALTAR_X, ALTAR_Y, 0));

        engine.addEntity(entity);
        return entity;
    }

    public Entity addAltarRight() {
        Entity entity = new Entity();
        SpriteComponent spriteComponent = new SpriteComponent(new Sprite());
        entity.add(spriteComponent);
        AnimationHelper.setAnimation(entity, Assets.animations.ALTAR_BLOODY);
        spriteComponent.sprite.flip(true, false);

        entity.add(new PositionComponent(RIGHT_ALTAR_X, ALTAR_Y, 0));

        engine.addEntity(entity);
        return entity;
    }

    public void addEntity(Entity entity) {
        engine.addEntity(entity);
    }

    public void dispose() {
        engine.removeAllEntities();
        engine.removeAllSystems();
    }
}
