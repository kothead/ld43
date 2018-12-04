package com.kothead.sacrifice.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.GdxJamGame;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.gdxjam.base.screen.ScreenBuilder;
import com.kothead.sacrifice.Assets;

public class MessageScreen extends BaseScreen implements InputProcessor {

    private BitmapFont font;
    private String message;
    private Label label;
    private ContinueCallback callback;

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

    public MessageScreen(GdxJamGame game, String message, ContinueCallback callback) {
        super(game);
        this.message = message;
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
        font = GdxJam.assets().get(Assets.fonts.DEFAULT);
        Skin skin = new Skin();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.RED;
        skin.add("default", style);
        label = new Label(message, skin);

        Table table = new Table();
        table.setFillParent(true);
        table.add(label).center();
        stage().addActor(table);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0.16f, 0.195f, 0.246f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        stage().act();
        stage().draw();
    }

    @Override
    public void dispose() {
        font.dispose();
        super.dispose();
    }

    public static class Builder implements ScreenBuilder<MessageScreen> {

        private String message;
        private ContinueCallback callback;

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setCallback(ContinueCallback callback) {
            this.callback = callback;
            return this;
        }

        @Override
        public MessageScreen build(GdxJamGame game) {
            return new MessageScreen(game, message, callback);
        }
    }
}
