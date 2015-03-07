package io.github.omgimanerd.shockwave.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import io.github.omgimanerd.shockwave.R;
import io.github.omgimanerd.shockwave.ShockwaveViewAnimator;
import io.github.omgimanerd.shockwave.game.Ball;
import io.github.omgimanerd.shockwave.game.Shockwave;
import io.github.omgimanerd.shockwave.util.PseudoButton;
import io.github.omgimanerd.shockwave.util.Util;

/**
 * Created by omgimanerd on 2/27/15.
 */
public class HowToPlayView extends View {

  public static int VIEW_ANIMATOR_INDEX;

  private ShockwaveViewAnimator viewAnimator_;
  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;

  private Paint instructionsPaint_;
  private PseudoButton backButton_;

  public HowToPlayView(Context context, ShockwaveViewAnimator viewAnimator) {
    super(context);

    viewAnimator_ = viewAnimator;
    ball_ = new Ball();
    shockwaves_ = new ArrayList<>();

    instructionsPaint_ = new Paint();
    instructionsPaint_.setColor(Util.TEXT_COLOR);
    instructionsPaint_.setTextAlign(Paint.Align.CENTER);

    backButton_ = new PseudoButton(
        PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT - PseudoButton.BUTTON_MARGIN - PseudoButton
        .BUTTON_HEIGHT,
        Util.SCREEN_WIDTH - PseudoButton.BUTTON_MARGIN,
        Util.SCREEN_HEIGHT - PseudoButton.BUTTON_MARGIN);
    backButton_.setButtonText(getResources().getString(R.string.back));
  }

  public void onDraw(Canvas canvas) {
    // TODO: render instructions in non-overflow manner
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

    // During instructions, the ball will bounce off what is supposed to be
    // the goal zone wall.
    if (ball_.getY() < ball_.getRadius()) {
      ball_.setVy(Math.abs(ball_.getVy()));
    } else if (ball_.getY() > Util.SCREEN_HEIGHT - ball_.getRadius()) {
      ball_.setVy(-Math.abs(ball_.getVy()));
    }

    backButton_.render(canvas);
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN) {
      if (backButton_.contains(event.getX(), event.getY())) {
        viewAnimator_.setDisplayedChild(MenuView.VIEW_ANIMATOR_INDEX);
      }
      shockwaves_.add(new Shockwave(event.getX(), event.getY()));
    }

    return super.onTouchEvent(event);
  }

}
