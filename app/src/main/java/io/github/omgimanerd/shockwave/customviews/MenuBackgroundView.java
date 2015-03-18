package io.github.omgimanerd.shockwave.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import io.github.omgimanerd.shockwave.game.Ball;
import io.github.omgimanerd.shockwave.game.Shockwave;
import io.github.omgimanerd.shockwave.util.Util;

import static java.lang.System.currentTimeMillis;

public class MenuBackgroundView extends View {

  private static final int MIN_SHOCKWAVE_INTERVAL = 500;

  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;

  private long lastShockwaveTime_;

  public MenuBackgroundView(Context context, AttributeSet attrs) {
    super(context, attrs);

    ball_ = new Ball(Util.SCREEN_WIDTH / 2, 0);
    ball_.setVx((float) (Math.random() * 10) - 5);
    ball_.setVy(5);
    shockwaves_ = new ArrayList<>();

    lastShockwaveTime_ = currentTimeMillis();
  }

  public void onDraw(Canvas canvas) {
    if (currentTimeMillis() > lastShockwaveTime_ + MIN_SHOCKWAVE_INTERVAL) {
      shockwaves_.add(new Shockwave(
          (float) (Math.random() * Util.SCREEN_WIDTH),
          (float) (Math.random() * Util.SCREEN_HEIGHT)));
      lastShockwaveTime_ = currentTimeMillis();
    }

    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).update();
      if (shockwaves_.get(i).isExpired()) {
        shockwaves_.remove(shockwaves_.get(i));
        i--;
      }
    }
    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).render(canvas);
    }

    ball_.update(shockwaves_);
    ball_.render(canvas);

    invalidate();
  }
}
