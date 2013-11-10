package com.truongtho.macd;

import java.util.regex.Pattern;

/** Created with IntelliJ IDEA. User: tho Date: 9/18/13 Time: 5:57 PM */
public class LinkTag extends ClickableTag {
  /* "[text](link)" = add link to text */
  private static final String MARKDOWN_REGEX_LINK = "\\[(.+?)\\]\\((.+?)\\)";

  @Override public String key() {
    return "link";
  }

  @Override public String getTextElement() {
    return "$1";
  }

  @Override protected Pattern getPattern() {
    return Pattern.compile(MARKDOWN_REGEX_LINK);
  }
}