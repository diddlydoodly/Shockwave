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
  private static final int GOAL_HEIGHT = 25;
  private static final int MIN_TAP_INTERVAL = 2000;

  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;
  private boolean canBlueTap_;
  private long lastBlueTapTime_;
  private boolean canRedTap_;
  private long lastRedTapTime_;

  private Paint borderPaint_;
  private Paint blueZonePaint_;
  private Paint blueGoalZonePaint_;
  private Paint redZonePaint_;
  private Paint redGoalZonePaint_;

  public Game() {
    ball_ = new Ball();
    shockwaves_ = new ArrayList<>();
    canRedTap_ = true;
    canBlueTap_ = true;

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
                    GameView.SCREEN_HEIGHT / 2, blueZonePaint_);
    canvas.drawRect(GAME_BORDER, 0,
                    GameView.SCREEN_WIDTH - GAME_BORDER,
                    GOAL_HEIGHT, blueGoalZonePaint_);
    canvas.drawRect(GAME_BORDER,
                    GameView.SCREEN_HEIGHT / 2,
                    GameView.SCREEN_WIDTH - GAME_BORDER,
                    GameView.SCREEN_HEIGHT, redZonePaint_);
    canvas.drawRect(GAME_BORDER,
                    GameView.SCREEN_HEIGHT - GOAL_HEIGHT,
                    GameView.SCREEN_WIDTH - GAME_BORDER,
                    GameView.SCREEN_HEIGHT, redGoalZonePaint_);

    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).render(canvas);
    }
    ball_.render(canvas);

    if (currentTimeMillis() > lastBlueTapTime_ + MIN_TAP_INTERVAL) {
      canBlueTap_ = true;
    }
    if (currentTimeMillis() > lastRedTapTime_ + MIN_TAP_INTERVAL) {
      canRedTap_ = true;
    }
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
    if (canBlueTap_ && validBlueArea.contains((int) x, (int) y)) {
      canBlueTap_ = false;
      canRedTap_ = true;
      lastBlueTapTime_ = currentTimeMillis();
      return true;
    } else if (canRedTap_ && validRedArea.contains((int) x, (int) y)) {
      canBlueTap_ = true;
      canRedTap_ = false;
      lastRedTapTime_ = currentTimeMillis();
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
    canRedTap_ = true;
    canBlueTap_ = true;
    ball_.reset();
  }

  /**
   * Returns 1 if red won, returns 2 if blue won, returns 0 if no one has
   * won yet.
   * @return
   */
  public int getWinner() {
    if (ball_.getY() <= 0) {
      return 2;
    } else if (ball_.getY() >= GameView.SCREEN_HEIGHT) {
      return 1;
    }
    return 0;
  }
}
