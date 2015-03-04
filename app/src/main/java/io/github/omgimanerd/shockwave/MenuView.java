package io.github.omgimanerd.shockwave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MenuView extends View {

  private ShockwaveView viewAnimator_;

  public MenuView(Context context, ShockwaveView viewAnimator) {
    super(context);
    viewAnimator_ = viewAnimator;
  }

  public void onDraw(Canvas canvas) {
    canvas.drawText("test", 100, 100, new Paint());
  }

  public void onLayout(boolean changed, int left, int top, int right,
                          int bottom) {
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();

    if (action == MotionEvent.ACTION_DOWN) {
      viewAnimator_.showNext();
    }

    return true;
  }

}
