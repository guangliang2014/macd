package com.truongtho.macd.sample;

import android.content.Context;
import android.util.AttributeSet;
import com.truongtho.macd.MacdTextParser;
import com.truongtho.macd.MacdTextView;

/**
 * Created with IntelliJ IDEA.
 * User: nguyentruongtho.sg@gmail.com
 * Date: 11/10/13
 * Time: 11:29 AM
 * Copyright @ the company that the author works for <3
 */
public class OtherMacdTextView extends MacdTextView {
  public OtherMacdTextView(Context context) {
    super(context);
  }

  public OtherMacdTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public OtherMacdTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override protected void init() {
    MacdTextParser parser = new MacdTextParser();
    parser.register(new LogoTag());
    setParser(parser);
  }
}
