package com.truongtho.macd;

import android.graphics.Typeface;
import android.text.style.StyleSpan;
import java.util.regex.Pattern;

/** Created with IntelliJ IDEA. User: tho Date: 9/18/13 Time: 3:10 PM */
public class ItalicTag extends SpanTag {
  /* "*xxx*" = xxx in italics */
  private static final String MARKDOWN_REGEX_ITALIC = "\\*(.+?)\\*";

  @Override public String getTextElement() {
    return "$1";
  }

  @Override protected Object getSpanElement(String textElement, String[] matchStrings) {
    return new StyleSpan(Typeface.ITALIC);
  }

  @Override protected Pattern getPattern() {
    return Pattern.compile(MARKDOWN_REGEX_ITALIC);
  }

  @Override public String key() {
    return "italic";
  }
}
