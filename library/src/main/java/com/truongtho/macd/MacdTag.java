package com.truongtho.macd;

import android.text.SpannableStringBuilder;

/** Created with IntelliJ IDEA. User: tho Date: 9/17/13 Time: 3:40 PM */
public interface MacdTag {
  SpannableStringBuilder process(SpannableStringBuilder source);

  String getTextElement();

  String key();
}
