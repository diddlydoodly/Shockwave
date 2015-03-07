package io.github.omgimanerd.shockwave.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import io.github.omgimanerd.shockwave.ShockwaveViewAnimator;

/**
 * Created by omgimanerd on 3/2/15.
 */
public class EndgameView extends View {

  public static int VIEW_ANIMATOR_INDEX;

  private ShockwaveViewAnimator viewAnimator_;
  private int winner_;

  public EndgameView(Context context, ShockwaveViewAnimator viewAnimator) {
    super(context);
    viewAnimator_ = viewAnimator;
  }

  public void onDraw(Canvas canvas) {

  }

  public void setWinner(int winner) {
    winner_ = winner;
  }
}
