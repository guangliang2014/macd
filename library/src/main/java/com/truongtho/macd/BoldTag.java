package com.truongtho.macd;

import android.text.style.StyleSpan;
import java.util.regex.Pattern;

/** Created with IntelliJ IDEA. User: tho Date: 9/18/13 Time: 2:16 PM */
public class BoldTag extends SpanTag {
  /* "**xxx**" = xxx in bold */
  private static final String MARKDOWN_REGEX_BOLD = "\\*\\*(.+?)\\*\\*";

  @Override public String getTextElement() {
    return "$1";
  }

  @Override protected Object getSpanElement(String textElement, String[] matchStrings) {
    return new StyleSpan(android.graphics.Typeface.BOLD);
  }

  @Override protected Pattern getPattern() {
    return Pattern.compile(MARKDOWN_REGEX_BOLD);
  }

  @Override public String key() {
    return "bold";
  }
}
