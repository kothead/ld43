package com.kothead.sacrifice;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kothead.gdxjam.base.data.loader.TextureRegionLoader;
import com.kothead.gdxjam.base.util.TextureAnimation;

public final class Assets {
  public static final AssetDescriptor[] ALL = {animations.JOE_WALK, animations.GOD_FACE, images.GOD_FACE_0, images.JOE_WALK_0, images.GOD_FACE_1, images.JOE_WALK_1, images.GOD_FACE_2, images.JOE_WALK_2, images.GOD_FACE_3, images.JOE_WALK_3, images.GOD_FACE_4, images.GOD_FACE_5, images.BACKGROUND, images.HAND_LEFT, images.HAND_RIGHT, images.MASK1, images.MASK2, images.MOON};

  public static final class animations {
    public static final AssetDescriptor<TextureAnimation> JOE_WALK = new AssetDescriptor<TextureAnimation>("animations/joe_walk.json", TextureAnimation.class);

    public static final AssetDescriptor<TextureAnimation> GOD_FACE = new AssetDescriptor<TextureAnimation>("animations/god_face.json", TextureAnimation.class);

    public static final AssetDescriptor[] ALL = {JOE_WALK, GOD_FACE};
  }

  public static final class images {
    public static final AssetDescriptor<TextureRegion> GOD_FACE_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 0));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_0 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 0));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 1));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 1));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 2));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 2));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 3));

    public static final AssetDescriptor<TextureRegion> JOE_WALK_3 = new AssetDescriptor<TextureRegion>("images/pack.atlas#joe_walk", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "joe_walk", 3));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_4 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 4));

    public static final AssetDescriptor<TextureRegion> GOD_FACE_5 = new AssetDescriptor<TextureRegion>("images/pack.atlas#god_face", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "god_face", 5));

    public static final AssetDescriptor<TextureRegion> BACKGROUND = new AssetDescriptor<TextureRegion>("images/pack.atlas#background", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "background"));

    public static final AssetDescriptor<TextureRegion> HAND_LEFT = new AssetDescriptor<TextureRegion>("images/pack.atlas#hand_left", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "hand_left"));

    public static final AssetDescriptor<TextureRegion> HAND_RIGHT = new AssetDescriptor<TextureRegion>("images/pack.atlas#hand_right", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "hand_right"));

    public static final AssetDescriptor<TextureRegion> MASK1 = new AssetDescriptor<TextureRegion>("images/pack.atlas#mask1", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "mask1"));

    public static final AssetDescriptor<TextureRegion> MASK2 = new AssetDescriptor<TextureRegion>("images/pack.atlas#mask2", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "mask2"));

    public static final AssetDescriptor<TextureRegion> MOON = new AssetDescriptor<TextureRegion>("images/pack.atlas#moon", TextureRegion.class, new TextureRegionLoader.TextureRegionParameter("images/pack.atlas", "moon"));

    public static final AssetDescriptor[] ALL = {GOD_FACE_0, JOE_WALK_0, GOD_FACE_1, JOE_WALK_1, GOD_FACE_2, JOE_WALK_2, GOD_FACE_3, JOE_WALK_3, GOD_FACE_4, GOD_FACE_5, BACKGROUND, HAND_LEFT, HAND_RIGHT, MASK1, MASK2, MOON};
  }
}
