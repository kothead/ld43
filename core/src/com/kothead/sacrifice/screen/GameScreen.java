package com.kothead.sacrifice.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.GdxJamGame;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.gdxjam.base.screen.ScreenBuilder;
import com.kothead.sacrifice.EntityManager;

public class GameScreen extends BaseScreen {

    private EntityManager manager;

    public GameScreen(GdxJamGame game) {
        super(game);
    }

    @Override
    protected void layoutViewsLandscape(int width, int height) {
        layout(width, height);
    }

    @Override
    protected void layoutViewsPortrait(int width, int height) {
        layout(width, height);
    }

    protected void layout(int width, int height) {
        manager = new EntityManager(GdxJam.engine(), this);
        manager.addPlayerHead();
        Entity leftHand = manager.getPlayerLeftHand();
        Entity rightHand = manager.getPlayerRightHand();

        manager.addBeam(leftHand);
        manager.addRay(rightHand);

        manager.addEntity(leftHand);
        manager.addEntity(rightHand);

        for (int i = 0; i < 10; i++) {
            manager.addFlyingJoe();
        }

        manager.addAltarLeft();
        manager.addAltarRight();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0.16f, 0.195f, 0.246f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
    }

    public static final class Builder implements ScreenBuilder<GameScreen> {

        @Override
        public GameScreen build(GdxJamGame game) {
            return new GameScreen(game);
        }
    }
}
