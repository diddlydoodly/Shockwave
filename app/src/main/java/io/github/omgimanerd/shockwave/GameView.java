package io.github.omgimanerd.shockwave;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import io.github.omgimanerd.shockwave.game.Game;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 * Blue = 1 (0 to SCREEN_HEIGHT / 2)
 * Red = 2 (SCREEN_HEIGHT to SCREEN_HEIGHT)
 */
public class GameView extends View {

  private static final int FPS = 80;
  public static float SCREEN_WIDTH;
  public static float SCREEN_HEIGHT;

  private static final int MENU_MODE = 0;
  private static final int GAME_MODE = 1;
  private static final int RESULT_MODE = 2;

  private long lastUpdateTime_;
  public int mode_;

  public Game game_;

  public GameView(Context context) {
    super(context);

    SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
    SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;

    lastUpdateTime_ = currentTimeMillis();
    mode_ = MENU_MODE;
    game_ = new Game();
  }

  public void onDraw(Canvas canvas) {
    switch (mode_) {
      case MENU_MODE:
        break;
      case GAME_MODE:
        if (currentTimeMillis() - lastUpdateTime_ >= 1000 / FPS) {
          game_.update();
          game_.render(canvas);
        }
        if (game_.getWinner() != 0) {
          mode_ = RESULT_MODE;
        }
        break;
      case RESULT_MODE:
        break;
    }
    invalidate();
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();

    switch (mode_) {
      case MENU_MODE:
        mode_ = GAME_MODE;
        break;
      case GAME_MODE:
        if (action == MotionEvent.ACTION_DOWN) {
          game_.createShockWave(event.getX(), event.getY());
        }
        break;
      case RESULT_MODE:
        game_.reset();
        mode_ = GAME_MODE;
        break;
    }
    return true;
  }
}
