package io.github.omgimanerd.shockwave;

import android.app.ActionBar;
import android.content.Context;
import android.widget.ViewAnimator;

/**
 * Created by omgimanerd on 3/2/15.
 */
public class ShockwaveView extends ViewAnimator {

  private GameView gameView_;
  private HowToPlayView howToPlayView_;

  public ShockwaveView(Context context) {
    super(context);

    gameView_ = new GameView(context, this);
    howToPlayView_ = new HowToPlayView(context, this);

    addView(howToPlayView_, ActionBar.LayoutParams.MATCH_PARENT);
    addView(gameView_, ActionBar.LayoutParams.MATCH_PARENT);
    showNext();
  }
}
