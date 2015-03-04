package io.github.omgimanerd.shockwave;

import android.app.ActionBar;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

/**
 * Created by omgimanerd on 3/2/15.
 */
public class ShockwaveView extends ViewAnimator {

  private Animation inAnimation_;
  private Animation outAnimation_;

  private EndgameView endgameView_;
  private GameView gameView_;
  private HowToPlayView howToPlayView_;
  private MenuView menuView_;

  public ShockwaveView(Context context) {
    super(context);

    inAnimation_ = AnimationUtils.loadAnimation(context,
        android.R.anim.fade_in);
    outAnimation_ = AnimationUtils.loadAnimation(context,
        android.R.anim.fade_out);

    setInAnimation(inAnimation_);
    setOutAnimation(outAnimation_);
    endgameView_ = new EndgameView(context, this);
    gameView_ = new GameView(context, this);
    howToPlayView_ = new HowToPlayView(context, this);
    menuView_ = new MenuView(context, this);

    addView(menuView_, ActionBar.LayoutParams.MATCH_PARENT);
    addView(gameView_, ActionBar.LayoutParams.MATCH_PARENT);
    showNext();
  }
}
