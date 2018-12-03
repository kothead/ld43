package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.component.FollowCameraComponent;
import com.kothead.gdxjam.base.component.PositionComponent;
import com.kothead.gdxjam.base.component.SpriteComponent;
import com.kothead.gdxjam.base.screen.BaseScreen;

public class CameraControlSystem extends EntitySystem {

    private static final float CAMERA_SPEED = 100.0f;

    private BaseScreen screen;
    private int levelWidth;
    private int levelHeight;
    private ImmutableArray<Entity> entities;

    public CameraControlSystem(int priority, BaseScreen screen, int levelWidth, int levelHeight) {
        super(priority);
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.screen = screen;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(
                PositionComponent.class,
                FollowCameraComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        if (entities.size() == 0) return;

        Camera camera = screen.getCamera();

        Entity entity = entities.first();
        Vector3 position = PositionComponent.mapper.get(entity).position;
        Vector2 half = new Vector2(screen.getWorldWidth() / 2.0f, screen.getWorldHeight() / 2.0f);
        Vector2 target = new Vector2();

        target.x = position.x;
        target.y = position.y;

        Vector2 size = new Vector2();
        if (SpriteComponent.mapper.has(entity)) {
            Sprite sprite = SpriteComponent.mapper.get(entity).sprite;
            size.set(sprite.getWidth() / 2.0f, sprite.getHeight() / 2.0f);

            target.x += size.x;
            target.y += size.y;
        }

        if (position.x + size.x < half.x) target.x = half.x;
        if (position.x + size.x > levelWidth - half.x) target.x = levelWidth - half.x;

        if (position.y + size.y < half.y) target.y = half.y;
        if (position.y + size.y > levelHeight - half.y) target.y = levelHeight - half.y;

        Vector3 delta = new Vector3();
        delta.x = calcCameraSpeed(camera.position.x, target.x, deltaTime);
        delta.y = calcCameraSpeed(camera.position.y, target.y, deltaTime);

        camera.position.add(delta);
        camera.update();
        screen.batch().setProjectionMatrix(camera.combined);
    }

    private float calcCameraSpeed(float position, float target, float deltaTime) {
        if (position == target) return 0.0f;

        float speed = CAMERA_SPEED * deltaTime;
        if (Math.abs(target - position) < speed) speed = Math.abs(target - position);

        return target > position ? speed : -speed;
    }
}
