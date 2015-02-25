package io.github.omgimanerd.shockwave;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GameActivity extends ActionBarActivity {

  GameView gameView_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    GameView gameView_ = new GameView(this);
    setContentView(gameView_);
  }
}
