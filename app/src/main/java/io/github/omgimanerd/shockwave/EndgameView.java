package io.github.omgimanerd.shockwave;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import io.github.omgimanerd.shockwave.game.Shockwave;

/**
 * Created by omgimanerd on 3/2/15.
 */
public class EndgameView extends View {

  private ShockwaveView viewAnimator_;
  private int winner_;

  public EndgameView(Context context, ShockwaveView viewAnimator) {
    super(context);
    viewAnimator_ = viewAnimator;
  }

  public void onDraw(Canvas canvas) {

  }

  public void setWinner(int winner) {
    winner_ = winner;
  }
}
