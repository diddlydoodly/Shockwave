package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import static java.lang.System.currentTimeMillis;

/**
 * Created by omgimanerd on 2/25/15.
 */
public class Shockwave {

  public static final float MAX_RADIUS = 50;
  private static final float DURATION = 500;
  private static final float STROKE_DRAW_WIDTH = 10;
  private static final int STROKE_COLOR = Color.parseColor("#ABCDEF");

  private float x_;
  private float y_;
  private float radius_;
  private long generationTime_;
  private float percentDone_;
  private Paint paint_;

  public Shockwave(float x, float y) {
    x_ = x;
    y_ = y;
    generationTime_ = currentTimeMillis();
    percentDone_ = 0;
    paint_ = new Paint();
    paint_.setStyle(Paint.Style.STROKE);
    paint_.setStrokeWidth(STROKE_DRAW_WIDTH);
    paint_.setColor(STROKE_COLOR);
  }

  public void update() {
    if (!isExpired()) {
      percentDone_ = (currentTimeMillis() - generationTime_) / DURATION;
      radius_ = percentDone_ * MAX_RADIUS;
      paint_.setAlpha((int) ((1 - percentDone_) * 255));
    }
  }

  public void render(Canvas canvas) {
    canvas.drawCircle(x_, y_, radius_, paint_);
  }

  public boolean isExpired() {
    return radius_ >= MAX_RADIUS;
  }

  public float getX() {
    return x_;
  }

  public float getY() {
    return y_;
  }

  public float[] getXY() {
    return new float[] {
        x_, y_
    };
  }

  public float getRadius() {
    return radius_;
  }

  public float getPercentDone() {
    return percentDone_;
  }
}
