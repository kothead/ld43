package com.kothead.sacrifice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.GdxJamGame;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.gdxjam.base.screen.ScreenBuilder;
import com.kothead.sacrifice.Assets;

public class TutorialScreen extends BaseScreen implements InputProcessor {

    private ContinueCallback callback;
    private Sprite sprite;

    public interface ContinueCallback {
        void onContinue();
        void onBack();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.ENTER:
            case Input.Keys.SPACE:
                callback.onContinue();
                return true;

            case Input.Keys.ESCAPE:
                callback.onBack();
                return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        callback.onContinue();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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

    public TutorialScreen(GdxJamGame game, ContinueCallback callback) {
        super(game);
        this.callback = callback;
    }

    @Override
    protected void layoutViewsLandscape(int width, int height) {
        layout();
    }

    @Override
    protected void layoutViewsPortrait(int width, int height) {
        layout();
    }

    protected void layout() {
        sprite = new Sprite(new Texture(Gdx.files.internal("tutorial.png")));

        Gdx.input.setInputProcessor(this);
        GdxJam.assets().get(Assets.sounds.WIN).play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0.16f, 0.195f, 0.246f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch().begin();
        sprite.draw(batch());
        batch().end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public static class Builder implements ScreenBuilder<TutorialScreen> {

        private ContinueCallback callback;

        public Builder setCallback(ContinueCallback callback) {
            this.callback = callback;
            return this;
        }

        @Override
        public TutorialScreen build(GdxJamGame game) {
            return new TutorialScreen(game, callback);
        }
    }
}
