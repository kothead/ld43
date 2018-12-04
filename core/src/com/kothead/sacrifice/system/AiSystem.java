package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.*;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.component.AggressivenessComponent;
import com.kothead.sacrifice.component.ControllableComponent;
import com.kothead.sacrifice.component.FlyingComponent;
import com.kothead.sacrifice.component.IntelligenceComponent;
import com.kothead.sacrifice.state.JoeState;

public class AiSystem extends IteratingSystem {

    private static final float SPEED_WALK = 30.0f;
    private static final float SPEED_FLY = 20.0f;
    public static final float TIME_TO_DECIDE = 1.0f;

    private static final int DISTANCE_FROM_BORDER = 16;
    private static final int DISTANCE_FROM_ENEMY = 80;

    private EntityManager manager;
    private ImmutableArray<Entity> enemies;

    private int levelWidth;
    private int levelHeight;

    public AiSystem(EntityManager manager, int priority, int levelWidth, int levelHeight) {
        super(Family.all(
                VelocityComponent.class,
                PositionComponent.class,
                IntelligenceComponent.class,
                StateMachineComponent.class
        ).get(), priority);

        this.manager = manager;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        enemies = engine.getEntitiesFor(Family.all(
                ControllableComponent.class,
                FollowCameraComponent.class
        ).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateMachine machine = StateMachineComponent.mapper.get(entity).stateMachine;
        JoeState state = (JoeState) machine.getCurrentState();

        IntelligenceComponent intelligenceComponent = IntelligenceComponent.mapper.get(entity);
        boolean isFlying = FlyingComponent.mapper.has(entity);

        Entity enemy = enemies.first();
        Sprite sprite = SpriteComponent.mapper.get(enemy).sprite;
        Vector3 enemyPosition = PositionComponent.mapper.get(enemy).position;
        Vector2 target = new Vector2(
                enemyPosition.x + sprite.getWidth() / 2.0f,
                enemyPosition.y + sprite.getHeight() / 2.0f
        );
        if (state == JoeState.THROW && intelligenceComponent.timeFromDecision >= TIME_TO_DECIDE) {
            manager.addSpear(entity, target);
        } else if (state != JoeState.IDLE && state != JoeState.WALK && state != JoeState.FLY) return;

        intelligenceComponent.timeFromDecision += deltaTime;

        Vector2 velocity = VelocityComponent.mapper.get(entity).velocity;
        Vector3 position = PositionComponent.mapper.get(entity).position;

        if (intelligenceComponent.timeFromDecision > TIME_TO_DECIDE) {
            intelligenceComponent.timeFromDecision = 0;

            if (AggressivenessComponent.mapper.has(entity)) {
                if (Math.random() < AggressivenessComponent.mapper.get(entity).aggressiveness) {
                    machine.changeState(JoeState.THROW);
                    velocity.x = 0;
                    return;
                }
            }

            if (isFlying) {
                velocity.x = Math.random() > 0.5f ? SPEED_FLY : -SPEED_FLY;
                velocity.y = Math.random() > 0.5f ? SPEED_FLY : -SPEED_FLY;
                if (state != JoeState.FLY) machine.changeState(JoeState.FLY);
            } else {
                velocity.x = Math.random() > 0.5f ? SPEED_WALK : -SPEED_WALK;
                if (state != JoeState.WALK) machine.changeState(JoeState.WALK);
            }
        }

        if (position.x < DISTANCE_FROM_BORDER && velocity.x < 0
                || target.x - position.x > DISTANCE_FROM_ENEMY) {
            velocity.x = isFlying ? SPEED_FLY : SPEED_WALK;
        }

        if (position.x > levelWidth - DISTANCE_FROM_BORDER && velocity.x > 0
                || position.x - target.x > DISTANCE_FROM_ENEMY) {
            velocity.x = isFlying ? -SPEED_FLY : -SPEED_WALK;
        }

        if (position.y < DISTANCE_FROM_BORDER && velocity.y < 0
                || isFlying && target.y - position.y > DISTANCE_FROM_ENEMY) {
            velocity.y = SPEED_FLY;
        }

        if (position.y > levelHeight - DISTANCE_FROM_BORDER && velocity.y > 0
                || isFlying && position.y - target.y > DISTANCE_FROM_ENEMY) {
            velocity.y = -SPEED_FLY;
        }
    }
}
