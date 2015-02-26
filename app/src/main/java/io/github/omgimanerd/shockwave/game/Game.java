package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

import io.github.omgimanerd.shockwave.GameView;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 */
public class Game {

  public static final int GAME_BORDER = 10;
  private static final int BORDER_COLOR = Color.parseColor("#CCCCCC");
  private static final int BLUE_ZONE_COLOR = Color.parseColor("#1976D2");
  private static final int BLUE_BORDER_COLOR = Color.BLUE;
  private static final int RED_ZONE_COLOR = Color.parseColor("#D32F2F");
  private static final int RED_BORDER_COLOR = Color.RED;
  private static final int GOAL_HEIGHT = 10;

  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;
  private int turn_;

  private Paint borderPaint_;
  private Paint blueZonePaint_;
  private Paint blueGoalZonePaint_;
  private Paint redZonePaint_;
  private Paint redGoalZonePaint_;

  public Game() {
    ball_ = new Ball();
    shockwaves_ = new ArrayList<>();
    turn_ = 1;

    borderPaint_ = new Paint();
    borderPaint_.setColor(BORDER_COLOR);

    blueZonePaint_ = new Paint();
    blueZonePaint_.setColor(BLUE_ZONE_COLOR);

    blueGoalZonePaint_ = new Paint();
    blueGoalZonePaint_.setColor(BLUE_BORDER_COLOR);

    redZonePaint_ = new Paint();
    redZonePaint_.setColor(RED_ZONE_COLOR);

    redGoalZonePaint_ = new Paint();
    redGoalZonePaint_.setColor(RED_BORDER_COLOR);
  }

  public void update() {
    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).update();
      if (shockwaves_.get(i).isExpired()) {
        shockwaves_.remove(shockwaves_.get(i));
        i--;
      }
    }
    ball_.update(shockwaves_);
  }

  public void render(Canvas canvas) {
    canvas.drawRect(0, 0, GameView.SCREEN_WIDTH, GameView.SCREEN_HEIGHT,
                    borderPaint_);
    canvas.drawRect(GAME_BORDER, 0,
                    GameView.SCREEN_WIDTH - GAME_BORDER,
                    GameView.SCREEN_HEIGHT / 2, redZonePaint_);
    canvas.drawRect(GAME_BORDER,
                    GameView.SCREEN_HEIGHT / 2,
                    GameView.SCREEN_WIDTH - GAME_BORDER,
                    GameView.SCREEN_HEIGHT, blueZonePaint_);

    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).render(canvas);
    }
    ball_.render(canvas);
  }

  private boolean isValidShockwavePoint(float x, float y) {
    Rect validBlueArea;
    validBlueArea = new Rect(0, 0,
                             (int) GameView.SCREEN_WIDTH,
                             (int) GameView.SCREEN_HEIGHT / 2);
    Rect validRedArea;
    validRedArea = new Rect(0,
                            (int) GameView.SCREEN_HEIGHT / 2,
                            (int) GameView.SCREEN_WIDTH,
                            (int) GameView.SCREEN_HEIGHT);
    if (turn_ == 0) {
      if (validBlueArea.contains((int) x, (int) y)) {
        turn_ = 2;
      } else {
        turn_ = 1;
      }
      return true;
    } else if (turn_ == 1 && validBlueArea.contains((int) x, (int) y)) {
      turn_ = 2;
      return true;
    } else if (turn_ == 2 && validRedArea.contains((int) x, (int) y)) {
      turn_ = 1;
      return true;
    }
    return false;
  }

  public void createShockWave(float x, float y) {
    if (isValidShockwavePoint(x, y)) {
      shockwaves_.add(new Shockwave(x, y));
    }
  }

  public void reset() {

  }

  /**
   * Returns 1 if blue won, returns 2 if blue won, returns 0 if no one has
   * won yet.
   * @return
   */
  public int getWinner() {
    Rect blueGoal = new Rect(0, 0,
                             (int) GameView.SCREEN_WIDTH,
                             GOAL_HEIGHT);
    Rect redGoal = new Rect(0,
                            (int) GameView.SCREEN_HEIGHT - GOAL_HEIGHT,
                            (int) GameView.SCREEN_WIDTH,
                            (int) GameView.SCREEN_HEIGHT);
    if (blueGoal.contains((int) ball_.getX(), (int) ball_.getY())) {
      return 1;
    } else if (redGoal.contains((int) ball_.getX(), (int) ball_.getY())) {
      return 2;
    }
    return 0;
  }
}
