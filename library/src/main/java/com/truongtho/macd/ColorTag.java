package com.truongtho.macd;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import java.util.regex.Pattern;

/** Created with IntelliJ IDEA. User: tho Date: 9/18/13 Time: 6:03 PM */
public class ColorTag extends SpanTag {
  /* "{color|text}" = text in specified color */
  private static final String MARKDOWN_REGEX_COLOR = "\\{(.+?)\\|(.+?)\\}";

  @Override public String key() {
    return "color";
  }

  @Override public String getTextElement() {
    return "$2";
  }

  @Override protected Object getSpanElement(String textElement, String[] matchStrings) {
    if (matchStrings.length >= 3) {
      return new ForegroundColorSpan(Color.parseColor(matchStrings[1]));
    } else {
      return null;
    }
  }

  @Override protected Pattern getPattern() {
    return Pattern.compile(MARKDOWN_REGEX_COLOR);
  }
}
