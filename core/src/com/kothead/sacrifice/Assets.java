package com.kothead.sacrifice;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kothead.gdxjam.base.data.loader.TextureRegionLoader;
import com.kothead.gdxjam.base.util.TextureAnimation;

public final class Assets {
  public static final AssetDescriptor[] ALL = {animations.JOE_DIE, animations.JOE_WALK, animations.WING_BURN, animations.ALTAR_BLOODY, animations.JOE_THROW, animations.GOD_FACE, animations.WING, images.ALTAR_0, images.GOD_FACE_0, images.JOE_DIE_0, images.JOE_THROW_0, images.JOE_WALK_0, images.WING_0, images.WING_BURN_0, images.ALTAR_1, images.GOD_FACE_1, images.JOE_DIE_1, images.JOE_THROW_1, images.JOE_WALK_1, images.WING_1, images.WING_BURN_1, images.ALTAR_2, images.GOD_FACE_2, images.JOE_DIE_2, images.JOE_THROW_2, images.JOE_WALK_2, images.WING_2, images.WING_BURN_2, images.ALTAR_3, images.GOD_FACE_3, images.JOE_DIE_3, images.JOE_THROW_3, images.JOE_WALK_3, images.WING_3, images.WING_BURN_3, images.ALTAR_4, images.GOD_FACE_4, images.JOE_DIE_4, images.JOE_THROW_4, images.WING_BURN_4, images.ALTAR_5, images.GOD_FACE_5, images.JOE_THROW_5, images.JOE_DIE_5, images.WING_BURN_5, images.ALTAR_6, images.JOE_DIE_6, images.ALTAR_7, images.JOE_DIE_7, images.ALTAR_8, images.JOE_DIE_8, images.JOE_DIE_9, images.JOE_DIE_10, images.JOE_DIE_11, images.BACKGROUND, images.BEAM, images.HAND_LEFT, images.HAND_RIGHT, images.JOE_TAKEN, images.MASK1, images.MASK2, images.MOON, images.RAY, images.SPEAR};

  public static final class animations {
    public static final AssetDescriptor<TextureAnimation> JOE_DIE = new AssetDescriptor<TextureAnimation>("animations/joe_die.json", TextureAnimation.class);

    public static final AssetDescriptor<TextureAnimation> JOE_WALK = new AssetDescriptor<TextureAnimation>("animations/joe_walk.json", TextureAnimation.class);

    public static final AssetDescriptor<TextureAnimation> WING_BURN = new AssetDescriptor<TextureAnimation>("animations/wing_burn.json", TextureAnimation.class);

    public static final AssetDescriptor<TextureAnimation> ALTAR_BLOODY = new AssetDescriptor<TextureAnimation>("animations/altar_bloody.json", TextureAnimation.class);

    public static final AssetDescriptor<TextureAnimation> JOE_THROW = new AssetDescriptor<TextureAnimation>("animations/joe_throw.json", TextureAnimation.class);

    public static final AssetDescriptor<TextureAnimation> GOD_FACE = new AssetDescriptor<TextureAnimation>("animations/god_face.json", TextureAnimation.class);

    public static final AssetDescriptor<TextureAnimation> WING = new AssetDescriptor<TextureAnimation>("animations/wing.json", TextureAnimation.class);

    public static final AssetDescriptor[] ALL = {JOE_DIE, JOE_WALK, WING_BURN, ALTAR_BLOODY, JOE_THROW, GOD_FACE, WING};
  }

  public static final class images {
    public static final AssetDescriptor<TextureRegion> ALTAR_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 0));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 0));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 0));

    public static final AssetDescriptor<TextureRegion> JOE_THROW_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_throw", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_throw", 0));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 0));

    public static final AssetDescriptor<TextureRegion> WING_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing", 0));

    public static final AssetDescriptor<TextureRegion> WING_BURN_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing_burn", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing_burn", 0));

    public static final AssetDescriptor<TextureRegion> ALTAR_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 1));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 1));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 1));

    public static final AssetDescriptor<TextureRegion> JOE_THROW_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_throw", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_throw", 1));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 1));

    public static final AssetDescriptor<TextureRegion> WING_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing", 1));

    public static final AssetDescriptor<TextureRegion> WING_BURN_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing_burn", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing_burn", 1));

    public static final AssetDescriptor<TextureRegion> ALTAR_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 2));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 2));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 2));

    public static final AssetDescriptor<TextureRegion> JOE_THROW_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_throw", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_throw", 2));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 2));

    public static final AssetDescriptor<TextureRegion> WING_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing", 2));

    public static final AssetDescriptor<TextureRegion> WING_BURN_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing_burn", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing_burn", 2));

    public static final AssetDescriptor<TextureRegion> ALTAR_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 3));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 3));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 3));

    public static final AssetDescriptor<TextureRegion> JOE_THROW_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_throw", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_throw", 3));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 3));

    public static final AssetDescriptor<TextureRegion> WING_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing", 3));

    public static final AssetDescriptor<TextureRegion> WING_BURN_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing_burn", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing_burn", 3));

    public static final AssetDescriptor<TextureRegion> ALTAR_4 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 4));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_4 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 4));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_4 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 4));

    public static final AssetDescriptor<TextureRegion> JOE_THROW_4 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_throw", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_throw", 4));

    public static final AssetDescriptor<TextureRegion> WING_BURN_4 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing_burn", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing_burn", 4));

    public static final AssetDescriptor<TextureRegion> ALTAR_5 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 5));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_5 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 5));

    public static final AssetDescriptor<TextureRegion> JOE_THROW_5 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_throw", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_throw", 5));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_5 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 5));

    public static final AssetDescriptor<TextureRegion> WING_BURN_5 = new AssetDescriptor<TextureRegion>("images/pack.atlas#wing_burn", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "wing_burn", 5));

    public static final AssetDescriptor<TextureRegion> ALTAR_6 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 6));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_6 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 6));

    public static final AssetDescriptor<TextureRegion> ALTAR_7 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 7));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_7 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 7));

    public static final AssetDescriptor<TextureRegion> ALTAR_8 = new AssetDescriptor<TextureRegion>("images/pack.atlas#altar", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "altar", 8));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_8 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 8));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_9 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 9));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_10 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 10));

    public static final AssetDescriptor<TextureRegion> JOE_DIE_11 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_die", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_die", 11));

    public static final AssetDescriptor<TextureRegion> BACKGROUND = new AssetDescriptor<TextureRegion>("images/pack.atlas#background", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "background"));

    public static final AssetDescriptor<TextureRegion> BEAM = new AssetDescriptor<TextureRegion>("images/pack.atlas#beam", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "beam"));

    public static final AssetDescriptor<TextureRegion> HAND_LEFT = new AssetDescriptor<TextureRegion>("images/pack.atlas#hand_left", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "hand_left"));

    public static final AssetDescriptor<TextureRegion> HAND_RIGHT = new AssetDescriptor<TextureRegion>("images/pack.atlas#hand_right", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "hand_right"));

    public static final AssetDescriptor<TextureRegion> JOE_TAKEN = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_taken", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_taken"));

    public static final AssetDescriptor<TextureRegion> MASK1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#mask1", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "mask1"));

    public static final AssetDescriptor<TextureRegion> MASK2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#mask2", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "mask2"));

    public static final AssetDescriptor<TextureRegion> MOON = new AssetDescriptor<TextureRegion>("images/pack.atlas#moon", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "moon"));

    public static final AssetDescriptor<TextureRegion> RAY = new AssetDescriptor<TextureRegion>("images/pack.atlas#ray", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "ray"));

    public static final AssetDescriptor<TextureRegion> SPEAR = new AssetDescriptor<TextureRegion>("images/pack.atlas#spear", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "spear"));

    public static final AssetDescriptor[] ALL = {ALTAR_0, GOD_FACE_0, JOE_DIE_0, JOE_THROW_0, JOE_WALK_0, WING_0, WING_BURN_0, ALTAR_1, GOD_FACE_1, JOE_DIE_1, JOE_THROW_1, JOE_WALK_1, WING_1, WING_BURN_1, ALTAR_2, GOD_FACE_2, JOE_DIE_2, JOE_THROW_2, JOE_WALK_2, WING_2, WING_BURN_2, ALTAR_3, GOD_FACE_3, JOE_DIE_3, JOE_THROW_3, JOE_WALK_3, WING_3, WING_BURN_3, ALTAR_4, GOD_FACE_4, JOE_DIE_4, JOE_THROW_4, WING_BURN_4, ALTAR_5, GOD_FACE_5, JOE_THROW_5, JOE_DIE_5, WING_BURN_5, ALTAR_6, JOE_DIE_6, ALTAR_7, JOE_DIE_7, ALTAR_8, JOE_DIE_8, JOE_DIE_9, JOE_DIE_10, JOE_DIE_11, BACKGROUND, BEAM, HAND_LEFT, HAND_RIGHT, JOE_TAKEN, MASK1, MASK2, MOON, RAY, SPEAR};
  }
}
