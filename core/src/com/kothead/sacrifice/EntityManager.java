package com.kothead.sacrifice;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.GdxJam;
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

    public static final int LEVEL_WIDTH = 600;
    public static final int LEVEL_HEIGHT = 800;
    private static final int LEVEL_GROUND = 32;

    private static final int LEFT_ALTAR_X = 200;
    private static final int RIGHT_ALTAR_X = 400;
    private static final int ALTAR_Y = 200;

    private static final float SPEED_SPEAR = 150.0f;
    private static final float DAMAGE_SPEAR = 100.0f;

    private static final float JOE_HIT_POINTS = 1000.0f;
    public static final float GOD_HIT_POINTS = 1000.0f;
    public static final float RESTORE_HP_PER_SACRIFICE = 200.0f;

    public static final float MAX_DISTANCE_X = 150.0f;
    public static final float MAX_DISTANCE_Y = 100.0f;

    public static final float DELETE_SPEAR_AFTER = 2.0f;

    private static final float STARTING_POWER_BEAM = 10.0f;
    private static final float STARTING_POWER_RAY = 300.0f;
    public static final float BEAM_SCALE_UP = 2.0f;
    public static final int EXP_FOR_SACRIFICE = 50;
    public static final int EXP_FOR_LEVEL = 100;
    public static final int EXP_LEVEL_INCREASE = 100;
    public static final int LEVEL_WIN = 5;

    public static final int STARTING_JOES_COUNT = 5;
    public static final int STARTING_FLYING_JOES_COUNT = 0;
    public static final int JOES_PER_WAVE_INCREASE = 3;
    public static final int FLYING_JOES_PER_WAVE_INCREASE = 2;

    private Engine engine;
    private BaseScreen screen;

    private boolean gameOver = false;
    private boolean gameWin = false;

    public EntityManager(Engine engine, BaseScreen screen) {
        this.engine = engine;
        this.screen = screen;
        registerSystems();
    }

    public void registerSystems() {
        int priority = 0;
        engine.addSystem(new WaveGeneratorSystem(priority++, this));
        engine.addSystem(new InputSystem(priority++, screen));
        engine.addSystem(new AiSystem(this, priority++, LEVEL_WIDTH, LEVEL_HEIGHT));
        engine.addSystem(new GravitySystem(priority++, LEVEL_GROUND));
        engine.addSystem(new MovementSystem(priority++));

        engine.addSystem(new HitscanTracerSystem(priority++, LEVEL_GROUND));
        engine.addSystem(new HitscanDetectionSystem(priority++));
        engine.addSystem(new DamageDetectionSystem(priority++, this));
        engine.addSystem(new SacrificialSystem(priority++));
        engine.addSystem(new CleanerSystem(priority++));

        engine.addSystem(new CameraControlSystem(priority++, screen, LEVEL_WIDTH, LEVEL_HEIGHT));
        engine.addSystem(new StateMachineSystem(priority++));
        engine.addSystem(new AnimationSystem(priority++));
        engine.addSystem(new HierarchyControlSystem(priority++));
        engine.addSystem(new BackgroundRenderSystem(priority++, screen));
        engine.addSystem(new RenderSystem(priority++, screen.batch()));
        engine.addSystem(new ForegroundRenderSystem(priority++, screen));
        engine.addSystem(new DebugRenderSystem(priority++, screen));
    }

    public Entity addPlayerHead(HealthComponent healthComponent) {
        Entity entity = new Entity();
//        AnimationHelper.setTexture(entity, Assets.images.MASK1);
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setAnimation(entity, Assets.animations.GOD_FACE);

        entity.add(new PositionComponent(LEVEL_WIDTH / 2.0f, LEVEL_HEIGHT / 2.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());
        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 32,
                34, 32,
                34, 66,
                0, 66

        })));
        entity.add(healthComponent);
//        entity.add(new DebugComponent(new Polygon(new float[] {
//                0, 0,
//                64, 0,
//                64, 64,
//                0, 64
//        })));

        engine.addEntity(entity);
        return entity;
    }

    public Entity getPlayerLeftHand(HealthComponent healthComponent) {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.HAND_RIGHT);

        entity.add(new OrientationComponent(OrientationComponent.Orientation.LEFT));
        entity.add(new PositionComponent(LEVEL_WIDTH / 2.0f - 40.0f, LEVEL_HEIGHT / 2.0f + 10.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());
        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                -20, 20,
                20, 20,
                20, 35,
                -20, 35
        })));
        entity.add(healthComponent);

        // cannot add it right away, because of ray

        return entity;
    }

    public Entity getPlayerRightHand(HealthComponent healthComponent) {
        Entity entity = new Entity();
        entity.add(new SpriteComponent(new GodSprite()));
        AnimationHelper.setTexture(entity, Assets.images.HAND_LEFT);

        entity.add(new OrientationComponent(OrientationComponent.Orientation.RIGHT));
        entity.add(new PositionComponent(LEVEL_WIDTH / 2.0f + 60.0f, LEVEL_HEIGHT / 2.0f - 10.0f, 0.0f));
        entity.add(new VelocityComponent(0.0f, 0.0f));
        entity.add(new ControllableComponent());
        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                -20, 20,
                20, 20,
                20, 35,
                -20, 35
        })));
        entity.add(healthComponent);

        // cannot add it right away, because of beam

        return entity;
    }

    public Entity addJoe() {
        Entity entity = new Entity();

        entity.add(new SpriteComponent(new Sprite()));
        entity.add(new PositionComponent(
                Math.random() > 0.5f ? -16.0f : LEVEL_WIDTH,
                32.0f,
                0.0f
        ));
        entity.add(new VelocityComponent(30.0f, 0.0f));
        entity.add(new GravityComponent());
        entity.add(new StateMachineComponent(new EntityStateMachine(entity, JoeState.WALK)));
        entity.add(new IntelligenceComponent());
        entity.add(new VictimComponent());
        entity.add(new AggressivenessComponent(0.05f));
        entity.add(new HealthComponent(JOE_HIT_POINTS));

        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 0, 16, 0, 16, 32, 0, 32
        })));
        engine.addEntity(entity);
        return entity;
    }

    public Entity addFlyingJoe() {
        Entity entity = new Entity();

        entity.add(new SpriteComponent(new Sprite()));
        entity.add(new PositionComponent(
                Math.random() > 0.5f ? -16.0f : LEVEL_WIDTH,
                (float) (32.0f + Math.random() * (LEVEL_HEIGHT - 32.0f)),
                0.0f
        ));
        entity.add(new VelocityComponent(30.0f, 0.0f));
        entity.add(new StateMachineComponent(new EntityStateMachine(entity, JoeState.WALK)));
        entity.add(new IntelligenceComponent());
        entity.add(new VictimComponent());
        entity.add(new AggressivenessComponent(0.1f));
        entity.add(new HealthComponent(JOE_HIT_POINTS));
        entity.add(new FlyingComponent());

        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 0, 16, 0, 16, 32, 0, 32
        })));
        engine.addEntity(entity);

        Entity wings = new Entity();
        AnimationHelper.setAnimation(wings, Assets.animations.WING);
        wings.add(new PositionComponent(0, 0, 0));
        wings.add(new AttachedToComponent(entity, -16, 0));
        wings.add(new VictimComponent());
        wings.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 0, 16, 0, 16, 32, 0, 32
        })));

        engine.addEntity(wings);

        return entity;
    }

    public Entity addSpear(Entity joe, Vector2 target) {
        Entity entity = new Entity();
        AnimationHelper.setTexture(entity, Assets.images.SPEAR);

        Vector3 position = new Vector3(PositionComponent.mapper.get(joe).position);
        position.x += SpriteComponent.mapper.get(joe).sprite.isFlipX() ? -8.0f : 16.0f;
        position.y += 16.0f;
        float dx = target.x - position.x;
        float dy = target.y - position.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        entity.add(new PositionComponent(position));
        entity.add(new VelocityComponent(
                (float) (SPEED_SPEAR * dx / distance),
                (float) (SPEED_SPEAR * dy / distance)
        ));
        entity.add(new GravityComponent());
        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 0, 4, 0, 4, 4, 0, 4
        })));
        entity.add(new ProjectileComponent(DAMAGE_SPEAR));
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
                hand.getComponent(PositionComponent.class).position,
                STARTING_POWER_RAY
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
                hand.getComponent(PositionComponent.class).position,
                STARTING_POWER_BEAM
        ));

        entity.add(new CollisionBoxComponent(new Polygon()));
        engine.addEntity(entity);
        return entity;
    }

    public Entity addAltarLeft(Entity hitscan) {
        Entity entity = new Entity();
        AnimationHelper.setAnimation(entity, Assets.animations.ALTAR_BLOODY);

        entity.add(new PositionComponent(LEFT_ALTAR_X, ALTAR_Y, 0));
        entity.add(new AltarComponent(hitscan));
        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 40,
                50, 40,
                50, 96,
                0, 96
        })));

        engine.addEntity(entity);
        return entity;
    }

    public Entity addAltarRight(Entity hitscan) {
        Entity entity = new Entity();
        SpriteComponent spriteComponent = new SpriteComponent(new Sprite());
        entity.add(spriteComponent);
        AnimationHelper.setAnimation(entity, Assets.animations.ALTAR_BLOODY);
        spriteComponent.sprite.flip(true, false);

        entity.add(new PositionComponent(RIGHT_ALTAR_X, ALTAR_Y, 0));
        entity.add(new AltarComponent(hitscan));
        entity.add(new CollisionBoxComponent(new Polygon(new float[] {
                0, 40,
                50, 40,
                50, 96,
                0, 96
        })));

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

    public void readyToGameOver() {
        this.gameOver = true;
    }

    public void readyToGameWin() {
        this.gameWin = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWin() {
        return gameWin;
    }
}
