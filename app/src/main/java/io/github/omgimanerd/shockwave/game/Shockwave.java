package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import io.github.omgimanerd.shockwave.util.Util;

import static java.lang.System.currentTimeMillis;

public class Shockwave {

  private static final float DURATION = 250;
  private static final float STROKE_DRAW_WIDTH = 10;
  private static final int STROKE_COLOR = Color.parseColor("#CCCCCC");

  private float x_;
  private float y_;
  private float radius_;
  private float maxRadius_;
  private long generationTime_;
  private float percentDone_;
  private Paint paint_;

  public Shockwave(float x, float y) {
    x_ = x;
    y_ = y;
    radius_ = 0;
    maxRadius_ = Util.SCREEN_WIDTH / 5;
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
      radius_ = percentDone_ * maxRadius_;
      paint_.setAlpha((int) ((1 - percentDone_) * 255));
    }
  }

  public void render(Canvas canvas) {
    canvas.drawCircle(x_, y_, radius_, paint_);
  }

  public boolean isExpired() {
    return radius_ >= maxRadius_;
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
