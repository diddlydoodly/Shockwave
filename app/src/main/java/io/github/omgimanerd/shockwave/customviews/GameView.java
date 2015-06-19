package io.github.omgimanerd.shockwave.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import io.github.omgimanerd.shockwave.GameActivity;
import io.github.omgimanerd.shockwave.game.Game;

import static java.lang.System.currentTimeMillis;

/**
 * Blue = 1 (0 to SCREEN_HEIGHT / 2)
 * Red = 2 (SCREEN_HEIGHT to SCREEN_HEIGHT)
 */
public class GameView extends View {

  private static final int FPS = 80;
  private static final int MAX_SCORE = 10;

  private long lastUpdateTime_;

  private Game game_;
  private boolean gameOver_;

  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);

    lastUpdateTime_ = currentTimeMillis();
    game_ = new Game();
    gameOver_ = false;
  }

  public void onDraw(Canvas canvas) {
    if (currentTimeMillis() - lastUpdateTime_ >= 1000 / FPS) {
      game_.update();
      game_.render(canvas);
    }

    int blueScore = game_.getBlueScore();
    int redScore = game_.getRedScore();
    if (blueScore >= MAX_SCORE) {
      ((GameActivity) getContext()).showLostOverlay(Game.WINNER_BLUE);
      gameOver_ = true;
    } else if (redScore >= MAX_SCORE) {
      ((GameActivity) getContext()).showLostOverlay(Game.WINNER_RED);
      gameOver_ = true;
    }
    invalidate();
  }

  // TODO: multi-touch
  public boolean onTouchEvent(MotionEvent event) {
    if (!gameOver_) {
      game_.onTouchEvent(event);
    }
    return super.onTouchEvent(event);
  }
}
