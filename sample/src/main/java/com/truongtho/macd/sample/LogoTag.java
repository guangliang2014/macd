package com.truongtho.macd.sample;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import com.truongtho.macd.MacdTag;

/**
 * Created with IntelliJ IDEA.
 * User: nguyentruongtho.sg@gmail.com
 * Date: 11/10/13
 * Time: 11:38 AM
 */
public class LogoTag implements MacdTag {
  private static final String LOGO_TAG = ":logo";

  @Override public SpannableStringBuilder process(SpannableStringBuilder source) {
    int pos = source.length();
    String nonSpanSource = source.toString();
    ImageSpan logoSpan = new ImageSpan(Application.context(), R.drawable.ic_launcher);

    while ((pos = nonSpanSource.lastIndexOf(LOGO_TAG, pos-1)) >= 0) {
      source.replace(pos, LOGO_TAG.length() + pos, " ");
      source.setSpan(logoSpan, pos, pos+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    return source;
  }

  @Override public String getTextElement() {
    return "$1";
  }

  @Override public String key() {
    return "logo";
  }
}
