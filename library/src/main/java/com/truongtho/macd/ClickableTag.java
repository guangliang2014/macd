package com.truongtho.macd;

import android.text.style.ClickableSpan;
import android.view.View;

/** Created with IntelliJ IDEA. User: tho Date: 9/18/13 Time: 11:44 AM */
public abstract class ClickableTag extends SpanTag {
  @Override protected Object getSpanElement(final String textElement, final String[] matchStrings) {
    ClickableSpan spanElement = new ClickableSpan() {
      @Override public void onClick(View view) {
        if (view instanceof MacdTextView) {
          MacdTextView.OnElementClickListener onElementClickListener = ((MacdTextView) view).getOnElementClickListener();
          onElementClickListener.onClick(view, textElement, key(), matchStrings);
        }
      }
    };
    return spanElement;
  }
}
