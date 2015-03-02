package io.github.omgimanerd.shockwave;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import io.github.omgimanerd.shockwave.game.Ball;
import io.github.omgimanerd.shockwave.game.Shockwave;
import io.github.omgimanerd.shockwave.game.Util;

/**
 * Created by omgimanerd on 2/27/15.
 */
public class HowToPlayView extends View {

  private ShockwaveView parentView_;
  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;

  public HowToPlayView(Context context, ShockwaveView parentView) {
    super(context);

    parentView_ = parentView;
    ball_ = new Ball();
    shockwaves_ = new ArrayList<>();
  }

  public void onDraw(Canvas canvas) {
    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).update();
      if (shockwaves_.get(i).isExpired()) {
        shockwaves_.remove(shockwaves_.get(i));
        i--;
      }
    }
    ball_.update(shockwaves_);

    // During instructions, the ball will bounce off what is supposed to be
    // the goal zone wall.
    if (ball_.getY() < ball_.getRadius()) {
      ball_.setVy(Math.abs(ball_.getVy()));
    } else if (ball_.getY() > Util.SCREEN_HEIGHT - ball_.getRadius()) {
      ball_.setVy(-Math.abs(ball_.getVy()));
    }
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN) {
      shockwaves_.add(new Shockwave(event.getX(), event.getY()));
    }
    return true;
  }

}
