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
  private static final int BALL_COLOR = Color.parseColor("#FFFFFF");
  private static final int BALL_RADIUS = 10;

  private float x_;
  private float y_;
  private float vx_;
  private float vy_;
  private Paint paint_;

  public Ball() {
    x_ = GameView.SCREEN_WIDTH / 2;
    y_ = GameView.SCREEN_HEIGHT / 2;

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
    if (x_ <= BALL_RADIUS) {
      vx_ *= -1;
    } else if (x_ > GameView.SCREEN_WIDTH - BALL_RADIUS) {
      vy_ *= -1;
    }
  }

  public void render(Canvas canvas) {
    canvas.drawCircle(x_, y_, BALL_RADIUS, paint_);
  }

  public boolean hasCollidedWith(Shockwave shockwave) {
    return Util.getDistance(getXY(), shockwave.getXY()) <=
        BALL_RADIUS + shockwave.getRadius();
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
}
