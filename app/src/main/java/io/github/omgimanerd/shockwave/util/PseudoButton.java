package io.github.omgimanerd.shockwave.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by omgimanerd on 3/5/15.
 */
public class PseudoButton {

  private static final int DEFAULT_R = 10;

  public static final int BUTTON_COLOR = Color.parseColor("#CCCCCC");
  public static final int BUTTON_MARGIN = 25;
  public static final int BUTTON_HEIGHT = 75;

  private RectF button_;
  private float r_;
  private Paint buttonPaint_;
  private Paint buttonTextPaint_;
  private String buttonText_;
  private int buttonTextChars_;

  public PseudoButton(float left, float top, float right, float bottom) {
    this(left, top, right, bottom, DEFAULT_R);
  }

  public PseudoButton(float left, float top, float right, float bottom,
                      float r) {
    this(new RectF(left, top, right, bottom), r);
  }

  public PseudoButton(RectF rect) {
    this(rect, DEFAULT_R);
  }

  public PseudoButton(RectF rect, float r) {
    button_ = rect;
    r_ = r;
    buttonPaint_ = new Paint();
    buttonPaint_.setColor(BUTTON_COLOR);
    buttonTextPaint_ = new Paint();
    buttonTextPaint_.setTextSize(Util.TEXT_SIZE);
    buttonTextPaint_.setTextAlign(Paint.Align.CENTER);
    buttonText_ = "";
  }

  public void render(Canvas canvas) {
    canvas.drawRoundRect(button_, r_, r_, buttonPaint_);
    canvas.drawText(buttonText_, 0, buttonTextChars_,
                    button_.centerX(),
                    button_.centerY() + buttonTextPaint_.getTextSize() / 2,
                    buttonTextPaint_);
  }

  public void setButtonColor(int color) {
    buttonPaint_.setColor(color);
  }

  public void setButtonColor(String color) {
    buttonPaint_.setColor(Color.parseColor(color));
  }

  public void setButtonText(String buttonText) {
    buttonText_ = buttonText;
    buttonTextChars_ = buttonPaint_.breakText(buttonText_, true,
                                              button_.width(), null);
  }

  public boolean contains(float x, float y) {
    return button_.contains(x, y);
  }

  public RectF getRectF() {
    return button_;
  }

  public float getLeft() {
    return button_.left;
  }

  public float getTop() {
    return button_.top;
  }

  public float getRight() {
    return button_.right;
  }

  public float getBottom() {
    return button_.bottom;
  }
}
