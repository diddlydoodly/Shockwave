package io.github.omgimanerd.shockwave.game;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by omgimanerd on 2/25/15.
 */
public class Game implements Drawable {

  private Ball ball_;
  private ArrayList<Shockwave> shockwaves_;

  public Game() {

  }

  public void update() {
    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).update();
    }
    ball_.update();
  }

  public void render(Canvas canvas) {
    for (int i = 0; i < shockwaves_.size(); ++i) {
      shockwaves_.get(i).render(canvas);
    }
    ball_.render(canvas);
  }

  public void createShockWave(float x, float y) {

  }

}
