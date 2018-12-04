package com.kothead.sacrifice.screen;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.GdxJamGame;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.gdxjam.base.screen.ScreenBuilder;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.GodGame;
import com.kothead.sacrifice.component.HealthComponent;

public class GameScreen extends BaseScreen {

    private EntityManager manager;
    private Music music;

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

        HealthComponent healthComponent = new HealthComponent(EntityManager.GOD_HIT_POINTS);
        manager.addPlayerHead(healthComponent);
        Entity leftHand = manager.getPlayerLeftHand(healthComponent);
        Entity rightHand = manager.getPlayerRightHand(healthComponent);

        Entity beam = manager.addBeam(leftHand);
        Entity ray = manager.addRay(rightHand);

        manager.addEntity(leftHand);
        manager.addEntity(rightHand);

        manager.addAltarLeft(beam);
        manager.addAltarRight(ray);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/music.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0.16f, 0.195f, 0.246f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        if (manager.isGameOver()) {
            ((GodGame) GdxJam.game()).showGameOverScreen();
        } else if (manager.isGameWin()) {
            ((GodGame) GdxJam.game()).showWinScreen();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        music.dispose();
    }

    public static final class Builder implements ScreenBuilder<GameScreen> {

        @Override
        public GameScreen build(GdxJamGame game) {
            return new GameScreen(game);
        }
    }
}
