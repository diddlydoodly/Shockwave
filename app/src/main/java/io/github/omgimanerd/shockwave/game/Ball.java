package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import io.github.omgimanerd.shockwave.GameView;

/**
 * Created by omgimanerd on 2/25/15.
 */
public class Ball {

  private static final float MAX_REFLECT_VELOCITY = 15;
  private static final int BALL_COLOR = Color.parseColor("#B6B6B6");

  private float x_;
  private float y_;
  private float vx_;
  private float vy_;
  private float radius_;
  private Paint paint_;

  public Ball() {
    x_ = GameView.SCREEN_WIDTH / 2;
    y_ = GameView.SCREEN_HEIGHT / 2;
    radius_ = GameView.SCREEN_WIDTH / 25;

    paint_ = new Paint();
    paint_.setColor(BALL_COLOR);
  }

  public void update(ArrayList<Shockwave> shockwaves) {
    x_ += vx_;
    y_ += vy_;
    for (int i = 0; i < shockwaves.size(); ++i) {
      Shockwave shockwave = shockwaves.get(i);
      if (hasCollidedWith(shockwave)) {
        float reflectAngle = (float) Math.atan2(y_ - shockwave.getY(),
                                                x_ - shockwave.getX());
        float newVelocity = Math.max((1 - shockwave.getPercentDone()) *
            MAX_REFLECT_VELOCITY, MAX_REFLECT_VELOCITY / 2);
        vx_ = (float) (newVelocity * Math.cos(reflectAngle));
        vy_ = (float) (newVelocity * Math.sin(reflectAngle));
      }
    }
    if (x_ <= radius_) {
      vx_ *= -1;
    } else if (x_ > GameView.SCREEN_WIDTH - radius_) {
      vx_ *= -1;
    }
  }

  public void render(Canvas canvas) {
    canvas.drawCircle(x_, y_, radius_, paint_);
  }

  public boolean hasCollidedWith(Shockwave shockwave) {
    return Util.getDistance(getXY(), shockwave.getXY()) <=
        radius_ + shockwave.getRadius();
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

  public void setX(float x) {
    x_ = x;
  }

  public void setY(float y) {
    y_ = y;
  }

  public void setXY(float[] xy) {
    x_ = xy[0];
    y_ = xy[1];
  }

  public float getRadius() {
    return radius_;
  }

  public void setRadius(float radius) {
    radius_ = radius;
  }

  public float getVy() {
    return vy_;
  }

  public void setVy(float vy) {
    vy_ = vy;
  }

  public float getVx() {
    return vx_;
  }

  public void setVx(float vx) {
    vx_ = vx;
  }

  public void reset() {
    x_ = GameView.SCREEN_WIDTH / 2;
    y_ = GameView.SCREEN_HEIGHT / 2;
    vx_ = 0;
    vy_ = 0;
  }
}
