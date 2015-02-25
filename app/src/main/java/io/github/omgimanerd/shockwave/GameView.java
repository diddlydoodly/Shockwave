package io.github.omgimanerd.shockwave;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import io.github.omgimanerd.shockwave.game.Game;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 */
public class GameView extends View {

  private static final int FPS = 80;
  public static float screenWidth_;
  public static float screenHeight_;

  private static final int MENU_MODE = 0;
  private static final int GAME_MODE = 1;

  private long lastUpdateTime_;
  public int mode_;

  public Game game_;

  public GameView(Context context) {
    super(context);

    screenWidth_ = getResources().getDisplayMetrics().widthPixels;
    screenHeight_ = getResources().getDisplayMetrics().heightPixels;

    lastUpdateTime_ = currentTimeMillis();
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
        break;
    }
    invalidate();
  }

  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();

    if (action == MotionEvent.ACTION_DOWN) {
      game_.createShockWave(event.getX(), event.getY());
    }
    return true;
  }
}
