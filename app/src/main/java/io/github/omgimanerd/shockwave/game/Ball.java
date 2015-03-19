package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import io.github.omgimanerd.shockwave.util.Util;

public class Ball {

  private static final float SCREEN_TO_MAX_VELOCITY_RATIO = 60;
  private static final int BALL_STROKE_COLOR = Color.parseColor("#4C4C4C");
  private static final int BALL_COLOR = Color.parseColor("#B6B6B6");
  private static final float BALL_RADIUS_RATIO = 0.04f;

  private float x_;
  private float y_;
  private float vx_;
  private float vy_;
  private float max_reflect_velocity_;
  private float radius_;
  private Paint paint_;

  // Since the Ball class will be used as a background in the menu and as a
  // demo in the instructions, this variable keeps track of whether or not
  // the ball is allowed to travel outside the top and bottom of the screen.
  private boolean allowToScore_;

  public Ball() {
    this(Util.SCREEN_WIDTH / 2, Util.SCREEN_HEIGHT / 2);
  }

  public Ball(float x, float y) {
    x_ = x;
    y_ = y;
    vx_ = 0;
    vy_ = 0;
    max_reflect_velocity_ = Util.SCREEN_HEIGHT / SCREEN_TO_MAX_VELOCITY_RATIO;
    radius_ = Util.SCREEN_WIDTH * BALL_RADIUS_RATIO;
    paint_ = new Paint();

    allowToScore_ = false;
  }

  public void update() {
    x_ += vx_;
    y_ += vy_;

    if (x_ <= radius_) {
      vx_ = Math.abs(vx_);
    } else if (x_ > Util.SCREEN_WIDTH - radius_) {
      vx_ = -Math.abs(vx_);
    }

    if (!allowToScore_) {
      if (y_ < radius_) {
        vy_ = Math.abs(vy_);
      } else if (y_ > Util.SCREEN_HEIGHT - radius_) {
        vy_ = -Math.abs(vy_);
      }
    }
  }

  public void update(ArrayList<Shockwave> shockwaves) {
    for (int i = 0; i < shockwaves.size(); ++i) {
      Shockwave shockwave = shockwaves.get(i);
      if (hasCollidedWith(shockwave)) {
        float reflectAngle = (float) Math.atan2(y_ - shockwave.getY(),
                                                x_ - shockwave.getX());
        float newVelocity = Math.max((1 - shockwave.getPercentDone()) *
            max_reflect_velocity_, max_reflect_velocity_ / 2);
        vx_ = (float) (newVelocity * Math.cos(reflectAngle));
        vy_ = (float) (newVelocity * Math.sin(reflectAngle));
      }
    }

    update();
  }

  public void render(Canvas canvas) {
    paint_.setStyle(Paint.Style.FILL);
    paint_.setColor(BALL_COLOR);
    canvas.drawCircle(x_, y_, radius_, paint_);

    paint_.setStyle(Paint.Style.STROKE);
    paint_.setStrokeWidth(radius_ / 10);
    paint_.setColor(BALL_STROKE_COLOR);
    canvas.drawCircle(x_, y_, radius_, paint_);
  }

  public boolean hasCollidedWith(Shockwave shockwave) {
    return Util.getDistance(getXY(), shockwave.getXY()) <=
        radius_ + shockwave.getRadius();
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

  public void setVy(float vy) {
    vy_ = vy;
  }

  public void setVx(float vx) {
    vx_ = vx;
  }

  public void setAllowToScore(boolean allowToScore) {
    allowToScore_ = allowToScore;
  }

  public void reset() {
    x_ = Util.SCREEN_WIDTH / 2;
    y_ = Util.SCREEN_HEIGHT / 2;
    vx_ = 0;
    vy_ = 0;
  }

  public void resetRed() {
    reset();
    y_ = Util.SCREEN_HEIGHT * 3 / 4;
  }

  public void resetBlue() {
    reset();
    y_ = Util.SCREEN_HEIGHT / 4;
  }
}
