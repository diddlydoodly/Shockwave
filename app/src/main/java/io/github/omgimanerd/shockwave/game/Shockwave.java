package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 */
public class Shockwave implements Drawable {

  private static final float DURATION = 500;
  private static final float MAX_RADIUS = 40;
  private static final float STROKE_DRAW_WIDTH = 10;
  private static final int STROKE_COLOR = Color.parseColor("#88000000");

  private float x_;
  private float y_;
  private float radius_;
  private long generationTime_;
  private long expirationTime_;
  private Paint paint_;

  public Shockwave(float x, float y) {
    x_ = x;
    y_ = y;
    generationTime_ = currentTimeMillis();
    expirationTime_ = (long) (generationTime_ + DURATION);
    paint_ = new Paint();
    paint_.setStyle(Paint.Style.STROKE);
    paint_.setStrokeWidth(STROKE_DRAW_WIDTH);
    paint_.setColor(STROKE_COLOR);
  }

  public void update() {
    if (!isExpired()) {
      long timeElapsed = expirationTime_ - currentTimeMillis();
      radius_ = (timeElapsed / DURATION) * MAX_RADIUS;
    }
  }

  public void render(Canvas canvas) {
    canvas.drawCircle(x_, y_, radius_, paint_);
  }

  public boolean isExpired() {
    return currentTimeMillis() > expirationTime_;
  }

}
