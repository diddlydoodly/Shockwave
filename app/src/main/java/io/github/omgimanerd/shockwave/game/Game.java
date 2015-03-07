package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;

import io.github.omgimanerd.shockwave.util.Util;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 */
public class Game {

  public static final int GAME_BORDER = 10;
  private static final int BORDER_COLOR = Color.parseColor("#CCCCCC");
  private static final int BLUE_ZONE_COLOR = Color.parseColor("#1976D2");
  private static final int BLUE_GOAL_ZONE_COLOR = Color.BLUE;
  private static final int RED_ZONE_COLOR = Color.parseColor("#D32F2F");
  private static final int RED_GOAL_ZONE_COLOR = Color.parseColor("#BF0A0A");
  private static final int GOAL_HEIGHT = 25;
  private static final int MIN_TAP_INTERVAL = 2000;

  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;

  private int blueScore_;
  private int redScore_;

  private boolean canBlueTap_;
  private long lastBlueTapTime_;
  private boolean canRedTap_;
  private long lastRedTapTime_;

  private RectF blueZone_;
  private RectF redZone_;

  private Paint borderPaint_;
  private Paint scoreTextPaint_;

  private Paint blueZonePaint_;
  private Paint blueGoalZonePaint_;
  private Paint redZonePaint_;
  private Paint redGoalZonePaint_;

  public Game() {
    ball_ = new Ball();
    shockwaves_ = new ArrayList<>();

    blueScore_ = 0;
    redScore_ = 0;
    canRedTap_ = true;
    canBlueTap_ = true;

    blueZone_ = new RectF(GAME_BORDER,
                          0,
                          Util.SCREEN_WIDTH - GAME_BORDER,
                          Util.SCREEN_HEIGHT / 2);
    redZone_ = new RectF(GAME_BORDER,
                         Util.SCREEN_HEIGHT / 2,
                         Util.SCREEN_WIDTH - GAME_BORDER,
                         Util.SCREEN_HEIGHT);

    borderPaint_ = new Paint();
    borderPaint_.setColor(BORDER_COLOR);
    scoreTextPaint_ = new Paint();
    scoreTextPaint_.setColor(Util.TEXT_COLOR);
    scoreTextPaint_.setTextAlign(Paint.Align.CENTER);
    scoreTextPaint_.setTextSize(Util.SCREEN_WIDTH / 10);

    blueZonePaint_ = new Paint();
    blueZonePaint_.setColor(BLUE_ZONE_COLOR);

    blueGoalZonePaint_ = new Paint();
    blueGoalZonePaint_.setColor(BLUE_GOAL_ZONE_COLOR);

    redZonePaint_ = new Paint();
    redZonePaint_.setColor(RED_ZONE_COLOR);

    redGoalZonePaint_ = new Paint();
    redGoalZonePaint_.setColor(RED_GOAL_ZONE_COLOR);
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

    if (getWinner() == 1) {
      redScore_++;
      softReset();
    } else if (getWinner() == 2) {
      blueScore_++;
      softReset();
    }
  }

  public void render(Canvas canvas) {
    canvas.drawRect(0, 0, Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT,
                    borderPaint_);

    // Render the blue zone, goal area, and score.
    canvas.drawRect(blueZone_, blueZonePaint_);
    canvas.drawRect(GAME_BORDER,
                    0,
                    Util.SCREEN_WIDTH - GAME_BORDER,
                    GOAL_HEIGHT,
                    blueGoalZonePaint_);
    // We draw the blue score upside down so that the blue can read it from
    // their perspective.
    canvas.save();
    canvas.rotate(180, Util.SCREEN_WIDTH / 2,
                  GOAL_HEIGHT + scoreTextPaint_.getTextSize());
    canvas.drawText(blueScore_ + "", Util.SCREEN_WIDTH / 2,
                    GOAL_HEIGHT + scoreTextPaint_.getTextSize(),
                    scoreTextPaint_);
    canvas.restore();

    // Render the red zone, goal area, and score.
    canvas.drawRect(redZone_, redZonePaint_);
    canvas.drawRect(GAME_BORDER,
                    Util.SCREEN_HEIGHT - GOAL_HEIGHT,
                    Util.SCREEN_WIDTH - GAME_BORDER,
                    Util.SCREEN_HEIGHT, redGoalZonePaint_);
    canvas.drawText(redScore_ + "", Util.SCREEN_WIDTH / 2,
                    Util.SCREEN_HEIGHT - GOAL_HEIGHT -
                        scoreTextPaint_.getTextSize(),
                    scoreTextPaint_);

    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).render(canvas);
    }
    ball_.render(canvas);

    // Updates the state of whether or not each player can tap depending on
    // the tap timer.
    if (currentTimeMillis() > lastBlueTapTime_ + MIN_TAP_INTERVAL) {
      canBlueTap_ = true;
    }
    if (currentTimeMillis() > lastRedTapTime_ + MIN_TAP_INTERVAL) {
      canRedTap_ = true;
    }
  }

  public void onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      createShockWave(event.getX(), event.getY());
    }
  }

  public void createShockWave(float x, float y) {
    if (canBlueTap_ && blueZone_.contains((int) x, (int) y)) {
      canBlueTap_ = false;
      canRedTap_ = true;
      lastBlueTapTime_ = currentTimeMillis();
      shockwaves_.add(new Shockwave(x, y));
    } else if (canRedTap_ && redZone_.contains((int) x, (int) y)) {
      canBlueTap_ = true;
      canRedTap_ = false;
      lastRedTapTime_ = currentTimeMillis();
      shockwaves_.add(new Shockwave(x, y));
    }
    return;
  }

  public void softReset() {
    canRedTap_ = true;
    canBlueTap_ = true;
    ball_.reset();
  }

  public void hardReset() {
    softReset();
    blueScore_ = 0;
    redScore_ = 0;
  }

  /**
   * Returns 1 if red won, returns 2 if blue won, returns 0 if no one has
   * won yet.
   * @return
   */
  public int getWinner() {
    if (ball_.getY() <= 0) {
      return 1;
    } else if (ball_.getY() >= Util.SCREEN_HEIGHT) {
      return 2;
    }
    return 0;
  }

  public int getBlueScore() {
    return blueScore_;
  }

  public int getRedScore() {
    return redScore_;
  }
}
