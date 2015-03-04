package io.github.omgimanerd.shockwave;

import android.app.Activity;
import android.os.Bundle;

import io.github.omgimanerd.shockwave.game.Util;

public class ShockwaveActivity extends Activity {

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
