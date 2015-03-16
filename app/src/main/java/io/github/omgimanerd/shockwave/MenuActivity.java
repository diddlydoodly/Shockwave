package io.github.omgimanerd.shockwave;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import io.github.omgimanerd.shockwave.util.Util;

public class MenuActivity extends Activity {

  private Button startButton_;
  private Button howToPlayButton_;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                         WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.menu_layout);

    Util.SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
    Util.SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;

    startButton_ = (Button) findViewById(R.id.startButton);
    howToPlayButton_ = (Button) findViewById(R.id.howToPlayButton);
    init();
  }

  private void init() {
    startButton_.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_slide_in_bottom,
                                  R.anim.abc_slide_out_bottom);
        finish();
      }
    });

    howToPlayButton_.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),
                                   HowToPlayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_slide_in_top,
                                  R.anim.abc_slide_out_top);
        finish();
      }
    });
  }
}
