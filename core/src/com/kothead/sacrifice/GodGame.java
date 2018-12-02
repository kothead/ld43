package com.kothead.sacrifice;

import com.kothead.gdxjam.base.GdxJamGame;
import com.kothead.gdxjam.base.context.DefaultContext;
import com.kothead.gdxjam.base.data.GdxJamConfiguration;
import com.kothead.gdxjam.base.screen.LoadingScreen;
import com.kothead.sacrifice.screen.GameScreen;

public class GodGame extends GdxJamGame {

	public GodGame() {
		super(new GameConfiguration());
	}

	@Override
	public void create () {
		super.create();

		showGameScreen();
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
						Assets.animations.JOE_WALK,
						Assets.animations.GOD_FACE
				)
		);
	}

	public static class GameConfiguration extends GdxJamConfiguration {
		GameConfiguration() {
			width = 640;
			height = 360;
		}
	}
}
