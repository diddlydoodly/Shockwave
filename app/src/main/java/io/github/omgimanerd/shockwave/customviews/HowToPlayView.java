package io.github.omgimanerd.shockwave.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import io.github.omgimanerd.shockwave.game.Ball;
import io.github.omgimanerd.shockwave.game.Shockwave;

public class HowToPlayView extends View {

  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;

  public HowToPlayView(Context context, AttributeSet attrs) {
    super(context, attrs);

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
    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).render(canvas);
    }

    ball_.update(shockwaves_);
    ball_.render(canvas);

    invalidate();
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();

    if (action == MotionEvent.ACTION_DOWN) {
      shockwaves_.add(new Shockwave(event.getX(), event.getY()));
    }

    return super.onTouchEvent(event);
  }
}
