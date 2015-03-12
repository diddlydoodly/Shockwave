package io.github.omgimanerd.shockwave;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import io.github.omgimanerd.shockwave.util.Util;

public class ShockwaveActivity extends Activity {

  private ShockwaveViewAnimator shockwaveViewAnimator_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Util.SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
    Util.SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;
    Util.TEXT_SIZE = getResources().getDimensionPixelSize(R.dimen.text_size);
    Util.TEXT_COLOR = Color.parseColor("#CCCCCC");

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);

    shockwaveViewAnimator_ = new ShockwaveViewAnimator(this);
    setContentView(shockwaveViewAnimator_);
  }
}
