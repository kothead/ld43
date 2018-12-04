package com.kothead.sacrifice.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.kothead.gdxjam.base.GdxJam;
import com.kothead.gdxjam.base.screen.BaseScreen;
import com.kothead.sacrifice.Assets;
import com.kothead.sacrifice.EntityManager;
import com.kothead.sacrifice.component.HealthComponent;
import com.kothead.sacrifice.component.HitscanComponent;

public class ForegroundRenderSystem extends EntitySystem {

    private static final int PADDING_X = 10;
    private static final int PADDING_Y = 5;
    private static final int BAR_HEIGHT = 5;
    private static final int BAR_WIDTH = 80;

    private BaseScreen screen;

    private Sprite barHealth = new Sprite(GdxJam.assets().get(Assets.images.HEALTH));
    private Sprite barRay = new Sprite(GdxJam.assets().get(Assets.images.RAY));
    private Sprite barBeam = new Sprite(GdxJam.assets().get(Assets.images.BEAM));
    private Sprite barBg = new Sprite(GdxJam.assets().get(Assets.images.BAR));

    private ImmutableArray<Entity> playerParts;
    private ImmutableArray<Entity> hitscans;

    private BitmapFont font;

    public ForegroundRenderSystem(int priority, BaseScreen screen) {
        super(priority);

        this.screen = screen;
        font = GdxJam.assets().get(Assets.fonts.DEFAULT);
        font.setUseIntegerPositions(false);
        font.setFixedWidthGlyphs("0123456789");
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        playerParts = engine.getEntitiesFor(Family.all(HealthComponent.class).get());
        hitscans = engine.getEntitiesFor(Family.all(HitscanComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        Vector3 focus = screen.getCamera().position;

        super.update(deltaTime);

        screen.batch().begin();

        float health = HealthComponent.mapper.get(playerParts.first()).healthPoints / EntityManager.GOD_HIT_POINTS;
        float ray = 0.0f;
        float beam = 0.0f;
        int rayLevel = 1;
        int beamLevel = 1;
        for (Entity entity: hitscans) {
            HitscanComponent hitscan = HitscanComponent.mapper.get(entity);
            float value = hitscan.exp / (float) (EntityManager.EXP_FOR_LEVEL + EntityManager.EXP_LEVEL_INCREASE * hitscan.level);

            switch (hitscan.type) {
                case RAY:
                    ray = value;
                    rayLevel = hitscan.level;
                    break;

                case BEAM:
                    beam = value;
                    beamLevel = hitscan.level;
                    break;
            }
        }

        barHealth.setPosition(
                focus.x - screen.getWorldWidth() / 2.0f + PADDING_X,
                screen.getWorldHeight() / 2.0f - PADDING_Y - BAR_HEIGHT + focus.y
        );
        barRay.setPosition(
                focus.x - screen.getWorldWidth() / 2.0f + PADDING_X,
                screen.getWorldHeight() / 2.0f - 2 * PADDING_Y - 2 * BAR_HEIGHT + focus.y
        );
        barBeam.setPosition(
                focus.x - screen.getWorldWidth() / 2.0f + PADDING_X,
                screen.getWorldHeight() / 2.0f - 3 * PADDING_Y - 3 * BAR_HEIGHT + focus.y
        );

        barHealth.setSize(BAR_WIDTH * health, BAR_HEIGHT);
        barRay.setSize(BAR_WIDTH * ray, BAR_HEIGHT);
        barBeam.setSize(BAR_WIDTH * beam, BAR_HEIGHT);

        barHealth.draw(screen.batch());
        barRay.draw(screen.batch());
        barBeam.draw(screen.batch());

        barBg.setPosition(barHealth.getX() + barHealth.getWidth(), barHealth.getY());
        barBg.setSize(BAR_WIDTH * (1 - health), BAR_HEIGHT);
        barBg.draw(screen.batch());

        barBg.setPosition(barRay.getX() + barRay.getWidth(), barRay.getY());
        barBg.setSize(BAR_WIDTH * (1 - ray), BAR_HEIGHT);
        barBg.draw(screen.batch());

        barBg.setPosition(barBeam.getX() + barBeam.getWidth(), barBeam.getY());
        barBg.setSize(BAR_WIDTH * (1 - beam), BAR_HEIGHT);
        barBg.draw(screen.batch());

        font.draw(screen.batch(), Integer.toString(rayLevel), barRay.getX() + BAR_WIDTH + PADDING_X, barRay.getY() + 16);
        font.draw(screen.batch(), Integer.toString(beamLevel), barBeam.getX() + BAR_WIDTH + PADDING_X, barBeam.getY() + 16);
        screen.batch().end();
    }
}
