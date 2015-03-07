package io.github.omgimanerd.shockwave.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import io.github.omgimanerd.shockwave.R;
import io.github.omgimanerd.shockwave.ShockwaveViewAnimator;
import io.github.omgimanerd.shockwave.util.PseudoButton;
import io.github.omgimanerd.shockwave.util.Util;

public class MenuView extends View {

  public static int VIEW_ANIMATOR_INDEX;

  private ShockwaveViewAnimator viewAnimator_;
  private PseudoButton startButton_;
  private PseudoButton howToPlayButton_;

  public MenuView(Context context, ShockwaveViewAnimator viewAnimator) {
    super(context);

    viewAnimator_ = viewAnimator;
    startButton_ = new PseudoButton(
        PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT / 4,
        Util.SCREEN_WIDTH - PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT / 4 + PseudoButton.BUTTON_HEIGHT);
    startButton_.setButtonColor(PseudoButton.BUTTON_COLOR);
    startButton_.setButtonText(getResources().getString(R.string.start_button));

    howToPlayButton_ = new PseudoButton(
        PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT / 2,
        Util.SCREEN_WIDTH - PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT / 2 + PseudoButton.BUTTON_HEIGHT);
    howToPlayButton_.setButtonColor(PseudoButton.BUTTON_COLOR);
    howToPlayButton_.setButtonText(getResources().getString(R.string.how_to_play));
  }

  public void onDraw(Canvas canvas) {
    startButton_.render(canvas);
    howToPlayButton_.render(canvas);
  }

  public void onLayout(boolean changed,
                       int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();

    if (action == MotionEvent.ACTION_DOWN) {
      if (startButton_.contains(event.getX(), event.getY())) {
        viewAnimator_.setDisplayedChild(GameView.VIEW_ANIMATOR_INDEX);
      }
      if (howToPlayButton_.contains(event.getX(), event.getY())) {
        viewAnimator_.setDisplayedChild(HowToPlayView.VIEW_ANIMATOR_INDEX);
      }
    }

    return super.onTouchEvent(event);
  }

}
