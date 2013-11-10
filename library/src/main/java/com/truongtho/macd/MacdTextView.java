package com.truongtho.macd;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/** Created with IntelliJ IDEA. User: tho Date: 9/17/13 Time: 11:18 AM */
public class MacdTextView extends TextView {
  private OnElementClickListener onElementClickListener;
  private MacdTextParser parser;

  //<editor-fold desc="Constructors">
  public MacdTextView(Context context) {
    super(context);
    init();
  }

  public MacdTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public MacdTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  //</editor-fold>

  @Override protected void onFinishInflate() {
    init();
    super.onFinishInflate();
  }

  private void init() {
  }

  @Override public void setText(CharSequence text, BufferType type) {
    if (parser == null) {
      setMovementMethod(LinkMovementMethod.getInstance());
      parser = new MacdTextParser();
    }
    text = parser.load(text.toString().trim()).parse();
    super.setText(text, type);
  }

  public OnElementClickListener getOnElementClickListener() {
    return onElementClickListener;
  }

  public void setOnElementClickListener(OnElementClickListener listener) {
    this.onElementClickListener = listener;
  }

  public static interface OnElementClickListener {
    void onClick(View textView, String data, String key, String[] matchStrings);
  }
}
