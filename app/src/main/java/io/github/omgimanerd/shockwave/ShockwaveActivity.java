package io.github.omgimanerd.shockwave;

import android.app.Activity;
import android.os.Bundle;

import io.github.omgimanerd.shockwave.game.Util;

public class ShockwaveActivity extends Activity {

  public static float SCREEN_WIDTH;
  public static float SCREEN_HEIGHT;

  private ShockwaveView shockwaveView_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Util.SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
    Util.SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;

    shockwaveView_ = new ShockwaveView(this);
    setContentView(shockwaveView_);
  }
}
