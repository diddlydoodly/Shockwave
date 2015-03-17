package io.github.omgimanerd.shockwave;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.omgimanerd.shockwave.game.Game;

/**
 * Created by omgimanerd on 3/16/15.
 */
public class GameActivity extends Activity {

  private RelativeLayout lostOverlay_;
  private TextView lostTextView_;
  private Button lostOverlayButton_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.game_layout);

    lostOverlay_ = (RelativeLayout) findViewById(R.id.lostOverlay);
    lostTextView_ = (TextView) findViewById(R.id.lostTextView);
    lostOverlayButton_ = (Button) findViewById(R.id.lostOverlayButton);

    init();
  }

  private void init() {
    lostOverlay_.setVisibility(View.GONE);

    lostOverlayButton_.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),
                                   MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_slide_in_bottom,
                                  R.anim.abc_slide_out_top);
        finish();
      }
    });
  }

  public void showLostOverlay(int winner) {
    if (winner == Game.WINNER_BLUE) {
      lostOverlay_.setBackground(getResources().getDrawable(
          R.drawable.rounded_layout_blue));
      lostTextView_.setText(getResources().getString(R.string.blue_wins));
    } else if (winner == Game.WINNER_RED) {
      lostOverlay_.setBackground(getResources().getDrawable(
          R.drawable.rounded_layout_red));
      lostTextView_.setText(getResources().getString(R.string.red_wins));
    } else {
      throw new Error(getResources().getString(R.string.error_msg));
    }

    lostOverlay_.setVisibility(View.VISIBLE);
  }
}
