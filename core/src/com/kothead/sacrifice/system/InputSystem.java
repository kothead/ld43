package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.FollowCameraComponent;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.gdxjam.base.component.VelocityComponent;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.component.ControllableComponent;
import com.kothead.sacrifice.component.OrientationComponent;

public class InputSystem extends EntitySystem implements InputProcessor {

    private static final float FLOAT_SPEED = 5000.0f;

    private BaseScreen screen;
    private ImmutableArray<Entity> entities;

    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private boolean isRayEnabled = false;
    private boolean isBeamEnabled = false;

    public InputSystem(int priority, BaseScreen screen) {
        super(priority);
        this.screen = screen;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        entities = engine.getEntitiesFor(Family.all(
                ControllableComponent.class,
                VelocityComponent.class).get());
        screen.addInputProcessor(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Entity followedEntity = null;
        Entity lastEntity = null;

        float minx = EntityManager.LEVEL_WIDTH;
        float miny = EntityManager.LEVEL_HEIGHT;
        float maxx = 0;
        float maxy = 0;

        for (Entity entity: entities) {
            VelocityComponent component = VelocityComponent.mapper.get(entity);

            Vector3 position = PositionComponent.mapper.get(entity).position;
            Sprite sprite = SpriteComponent.mapper.get(entity).sprite;

            float x = position.x + sprite.getWidth() / 2.0f;
            float y = position.y + sprite.getHeight() / 2.0f;

            if (x < minx) minx = x;
            if (y < miny) miny = y;
            if (x > maxx) maxx = x;
            if (y > maxy) maxy = y;

            component.velocity.x = 0;
            component.velocity.y = 0;

            if (FollowCameraComponent.mapper.has(entity)) {
                followedEntity = entity;
            }

            // move only head or hands corresponding to mouse buttons pressed
            if (OrientationComponent.mapper.has(entity)) {
                OrientationComponent.Orientation orientation = OrientationComponent.mapper.get(entity).orientation;
                if (isRayEnabled && isBeamEnabled
                        || orientation == OrientationComponent.Orientation.LEFT && !isRayEnabled
                        || orientation == OrientationComponent.Orientation.RIGHT && !isBeamEnabled) {
                    continue;
                }
            } else if (isRayEnabled ^ isBeamEnabled) {
                continue;
            }

            float dx = FLOAT_SPEED * deltaTime;
            if (movingRight) component.velocity.x = +dx;
            if (movingLeft) component.velocity.x -= dx;
            if (movingUp) component.velocity.y += dx;
            if (movingDown) component.velocity.y -= dx;

            if (lastEntity == null || !OrientationComponent.mapper.has(entity)) {
                lastEntity = entity;
            }
        }

        for (Entity entity: entities) {
            Sprite sprite = SpriteComponent.mapper.get(entity).sprite;

            Vector3 position = PositionComponent.mapper.get(entity).position;
            Vector2 velocity = VelocityComponent.mapper.get(entity).velocity;

            float x = position.x + velocity.x * Gdx.graphics.getDeltaTime() + sprite.getWidth() / 2.0f;
            float y = position.y + velocity.y * Gdx.graphics.getDeltaTime() + sprite.getHeight() / 2.0f;

            if (x - minx > EntityManager.MAX_DISTANCE_X && velocity.x > 0) velocity.x = 0;
            if (maxx - x > EntityManager.MAX_DISTANCE_X && velocity.x < 0) velocity.x = 0;
            if (y - miny > EntityManager.MAX_DISTANCE_Y && velocity.y > 0) velocity.y = 0;
            if (maxy - y > EntityManager.MAX_DISTANCE_Y && velocity.y < 0) velocity.y = 0;
        }

        if (lastEntity != null && followedEntity != lastEntity) {
            Component component = null;
            if (followedEntity != null) {
                component = followedEntity.remove(FollowCameraComponent.class);
            }
            if (component == null) {
                component = new FollowCameraComponent();
            }
            lastEntity.add(component);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                movingLeft = true;
                return true;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                movingRight = true;
                return true;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                movingDown = true;
                return true;

            case Input.Keys.UP:
            case Input.Keys.W:
                movingUp = true;
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                movingLeft = false;
                return true;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                movingRight = false;
                return true;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                movingDown = false;
                return true;

            case Input.Keys.UP:
            case Input.Keys.W:
                movingUp = false;
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (button) {
            case Input.Buttons.LEFT:
                isRayEnabled = true;
                return true;

            case Input.Buttons.RIGHT:
                isBeamEnabled = true;
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        switch (button) {
            case Input.Buttons.LEFT:
                isRayEnabled = false;
                return true;

            case Input.Buttons.RIGHT:
                isBeamEnabled = false;
                return true;

            default:
                return false;
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
