package com.kothead.sacrifice;

import com.badlogic.gdx.Gdx;
import com.kothead.gdxjam.base.GdxJamGame;
import com.kothead.gdxjam.base.context.DefaultContext;
import com.kothead.gdxjam.base.data.GdxJamConfiguration;
import com.kothead.gdxjam.base.screen.LoadingScreen;
import com.kothead.sacrifice.screen.GameScreen;
import com.kothead.sacrifice.screen.MessageScreen;
import com.kothead.sacrifice.screen.TutorialScreen;

public class GodGame extends GdxJamGame {

	public GodGame() {
		super(new GameConfiguration());
	}

	@Override
	public void create () {
		super.create();

		showTitleScreen();
	}

	public void showGameScreen() {
		getStateMachine().changeState(
				DefaultContext.create(
						new GameScreen.Builder(),
						new LoadingScreen.Builder(),
						Assets.images.BACKGROUND,
						Assets.images.MASK1,
						Assets.images.MASK2,
						Assets.images.MOON,
						Assets.images.HAND_LEFT,
						Assets.images.HAND_RIGHT,
						Assets.images.BEAM,
						Assets.images.RAY,
						Assets.images.HEALTH,
						Assets.images.BAR,
						Assets.images.SPEAR,
						Assets.images.JOE_TAKEN,
						Assets.images.JOE_WALK_0,
						Assets.animations.JOE_WALK,
						Assets.animations.JOE_DIE,
						Assets.animations.JOE_THROW,
						Assets.animations.GOD_FACE,
						Assets.animations.ALTAR_BLOODY,
                        Assets.animations.WING,
                        Assets.animations.WING_BURN,
						Assets.sounds.BURN,
						Assets.sounds.GAMEOVER,
						Assets.sounds.HIT,
						Assets.sounds.SACRIFICE,
						Assets.sounds.WIN,
						Assets.sounds.LEVEL,
						Assets.fonts.DEFAULT
				)
		);
	}

	public void showTitleScreen() {
		getStateMachine().changeState(
				DefaultContext.create(
						new MessageScreen.Builder()
								.setMessage("FALSE  GOD")
								.setCallback(new MessageScreen.ContinueCallback() {
									@Override
									public void onContinue() {
										showTutorialScreen();
									}

									@Override
									public void onBack() {
										Gdx.app.exit();
									}
								}),
						new LoadingScreen.Builder(),
						Assets.fonts.DEFAULT,
						Assets.sounds.GAMEOVER
		));
	}

	public void showGameOverScreen() {
		getStateMachine().changeState(
				DefaultContext.create(
						new MessageScreen.Builder()
								.setMessage("I  DECLINE  YOUR  SACRIFICE")
								.setCallback(new MessageScreen.ContinueCallback() {
									@Override
									public void onContinue() {
										showGameScreen();
									}

									@Override
									public void onBack() {
										Gdx.app.exit();
									}
								}),
						new LoadingScreen.Builder(),
						Assets.fonts.DEFAULT,
						Assets.sounds.GAMEOVER
				));
	}

	public void showWinScreen() {
		getStateMachine().changeState(
				DefaultContext.create(
						new MessageScreen.Builder()
								.setMessage("I  ACCEPT  YOUR  SACRIFICE")
								.setCallback(new MessageScreen.ContinueCallback() {
									@Override
									public void onContinue() {
										showGameScreen();
									}

									@Override
									public void onBack() {
										Gdx.app.exit();
									}
								}),
						new LoadingScreen.Builder(),
						Assets.fonts.DEFAULT,
						Assets.sounds.GAMEOVER
				));
	}

	public void showTutorialScreen() {
		getStateMachine().changeState(
				DefaultContext.create(
						new TutorialScreen.Builder()
								.setCallback(new TutorialScreen.ContinueCallback() {
									@Override
									public void onContinue() {
										showGameScreen();
									}

									@Override
									public void onBack() {
										Gdx.app.exit();
									}
								}),
						new LoadingScreen.Builder(),
						Assets.fonts.DEFAULT,
						Assets.sounds.WIN
				));
	}

	public static class GameConfiguration extends GdxJamConfiguration {
		GameConfiguration() {
			width = 720;
			height = 1280;
		}
	}
}
