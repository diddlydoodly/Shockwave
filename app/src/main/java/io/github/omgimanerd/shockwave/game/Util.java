package io.github.omgimanerd.shockwave.game;

/**
 * Created by omgimanerd on 2/26/15.
 */
public class Util {

  public static float SCREEN_WIDTH;
  public static float SCREEN_HEIGHT;

  public static float getDistance(float[] p1, float[] p2) {
    return (float) Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) +
                             (p1[1] - p2[1]) * (p1[1] - p2[1]));
  }

}
