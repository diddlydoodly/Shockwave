package io.github.omgimanerd.shockwave;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import io.github.omgimanerd.shockwave.game.Game;
import io.github.omgimanerd.shockwave.game.Shockwave;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 * Blue = 1 (0 to SCREEN_HEIGHT / 2)
 * Red = 2 (SCREEN_HEIGHT to SCREEN_HEIGHT)
 */
public class GameView extends View {

  private static final int FPS = 80;

  private ShockwaveView parentView_;
  private long lastUpdateTime_;

  public Game game_;

  public GameView(Context context, ShockwaveView parentView) {
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
    if (game_.getWinner() != 0) {
      parentView_.showNext();
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
