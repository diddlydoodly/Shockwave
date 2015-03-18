package io.github.omgimanerd.shockwave.util;

import android.graphics.Color;

public class Util {

  public static float SCREEN_WIDTH;
  public static float SCREEN_HEIGHT;
  public static int TEXT_COLOR = Color.parseColor("#CCCCCC");

  public static float getDistance(float[] p1, float[] p2) {
    return (float) Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) +
                             (p1[1] - p2[1]) * (p1[1] - p2[1]));
  }

}
