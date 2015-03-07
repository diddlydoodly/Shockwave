package io.github.omgimanerd.shockwave;

import android.app.ActionBar;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

import io.github.omgimanerd.shockwave.views.EndgameView;
import io.github.omgimanerd.shockwave.views.GameView;
import io.github.omgimanerd.shockwave.views.HowToPlayView;
import io.github.omgimanerd.shockwave.views.MenuView;

/**
 * Created by omgimanerd on 3/2/15.
 */
public class ShockwaveViewAnimator extends ViewAnimator {

  private Animation inAnimation_;
  private Animation outAnimation_;

  private EndgameView endgameView_;
  private GameView gameView_;
  private HowToPlayView howToPlayView_;
  private MenuView menuView_;

  public ShockwaveViewAnimator(Context context) {
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

    addView(endgameView_, ActionBar.LayoutParams.MATCH_PARENT);
    EndgameView.VIEW_ANIMATOR_INDEX = 0;
    addView(gameView_, ActionBar.LayoutParams.MATCH_PARENT);
    GameView.VIEW_ANIMATOR_INDEX = 1;
    addView(howToPlayView_, ActionBar.LayoutParams.MATCH_PARENT);
    HowToPlayView.VIEW_ANIMATOR_INDEX = 2;
    addView(menuView_, ActionBar.LayoutParams.MATCH_PARENT);
    MenuView.VIEW_ANIMATOR_INDEX = 3;

    setDisplayedChild(MenuView.VIEW_ANIMATOR_INDEX);
  }

  public EndgameView getEndgameView() {
    return endgameView_;
  }

  public GameView getGameView() {
    return gameView_;
  }

  public HowToPlayView getHowToPlayView() {
    return howToPlayView_;
  }

  public MenuView getMenuView() {
    return menuView_;
  }
}
