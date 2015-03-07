package io.github.omgimanerd.shockwave.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import io.github.omgimanerd.shockwave.ShockwaveViewAnimator;
import io.github.omgimanerd.shockwave.game.Game;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 * Blue = 1 (0 to SCREEN_HEIGHT / 2)
 * Red = 2 (SCREEN_HEIGHT to SCREEN_HEIGHT)
 */
public class GameView extends View {

  public static int VIEW_ANIMATOR_INDEX;
  private static final int FPS = 80;
  private static final int MAX_SCORE = 10;

  private ShockwaveViewAnimator parentView_;
  private long lastUpdateTime_;

  public Game game_;

  public GameView(Context context, ShockwaveViewAnimator parentView) {
    super(context);

    parentView_ = parentView;
    lastUpdateTime_ = currentTimeMillis();
    game_ = new Game();
  }

  public void onDraw(Canvas canvas) {
    if (currentTimeMillis() - lastUpdateTime_ >= 1000 / FPS) {
      game_.update();
      game_.render(canvas);
    }

    int blueScore = game_.getBlueScore();
    int redScore = game_.getRedScore();
    if (blueScore >= 10 || redScore >= 10) {
      parentView_.setDisplayedChild(EndgameView.VIEW_ANIMATOR_INDEX);
      if (blueScore >= 10) {
        parentView_.getEndgameView().setWinner(0);
      } else if (redScore >= 10) {
        parentView_.getEndgameView().setWinner(1);
      }
      game_.hardReset();
    }
    invalidate();
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();

    game_.onTouchEvent(event);

    return super.onTouchEvent(event);
  }
}
