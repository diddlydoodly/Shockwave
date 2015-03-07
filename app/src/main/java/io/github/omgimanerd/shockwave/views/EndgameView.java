package io.github.omgimanerd.shockwave.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import io.github.omgimanerd.shockwave.R;
import io.github.omgimanerd.shockwave.ShockwaveViewAnimator;
import io.github.omgimanerd.shockwave.util.PseudoButton;
import io.github.omgimanerd.shockwave.util.Util;

/**
 * Created by omgimanerd on 3/2/15.
 */
public class EndgameView extends View {

  public static int VIEW_ANIMATOR_INDEX;

  private ShockwaveViewAnimator viewAnimator_;

  private RectF background_;
  private Paint backgroundPaint_;
  private Paint textPaint_;
  private String textPaintText_;

  private PseudoButton mainMenuButton_;

  public EndgameView(Context context, ShockwaveViewAnimator viewAnimator) {
    super(context);
    viewAnimator_ = viewAnimator;

    background_ = new RectF(0, 0, Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT);
    backgroundPaint_ = new Paint();
    textPaint_ = new Paint();
    textPaint_.setTextSize(Util.TEXT_SIZE);
    textPaint_.setTextAlign(Paint.Align.CENTER);

    mainMenuButton_ = new PseudoButton(
        PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT / 2,
        Util.SCREEN_WIDTH-  PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT / 2 + PseudoButton.BUTTON_HEIGHT);
    mainMenuButton_.setButtonColor(PseudoButton.BUTTON_COLOR);
    mainMenuButton_.setButtonText(getResources().getString(R.string.main_menu));
  }

  public void onDraw(Canvas canvas) {
    canvas.drawRect(background_, backgroundPaint_);
    canvas.drawText(textPaintText_,
                    Util.SCREEN_WIDTH,
                    Util.SCREEN_HEIGHT / 4,
                    textPaint_);

    mainMenuButton_.render(canvas);
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();

    if (action == MotionEvent.ACTION_DOWN) {
      if (mainMenuButton_.contains(event.getX(), event.getY())) {
        viewAnimator_.setDisplayedChild(MenuView.VIEW_ANIMATOR_INDEX);
      }
    }

    return super.onTouchEvent(event);
  }

  /**
   * Winner is 0 if blue won, 1 if red won.
   * @param winner
   */
  public void setWinner(int winner) {
    if (winner == 0) {
      backgroundPaint_.setColor(Color.BLUE);
      textPaintText_ = getResources().getString(R.string.blue_wins);
    } else {
      backgroundPaint_.setColor(Color.RED);
      textPaintText_ = getResources().getString(R.string.red_wins);
    }
  }
}
