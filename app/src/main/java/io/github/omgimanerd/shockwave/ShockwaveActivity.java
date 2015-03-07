package io.github.omgimanerd.shockwave;

import android.app.Activity;
import android.os.Bundle;

import io.github.omgimanerd.shockwave.util.Util;

public class ShockwaveActivity extends Activity {

  private ShockwaveViewAnimator shockwaveViewAnimator_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Util.SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
    Util.SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels -
        getResources().getDimensionPixelSize(R.dimen.margin_bottom);
    Util.TEXT_SIZE = getResources().getDimensionPixelSize(R.dimen.text_size);

    shockwaveViewAnimator_ = new ShockwaveViewAnimator(this);
    setContentView(shockwaveViewAnimator_);
  }
}
