package com.truongtho.macd.sample;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.truongtho.macd.MacdTextView;

public class MacdActivity extends Activity {
  private MacdTextView macdTextView;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    macdTextView = (MacdTextView) findViewById(R.id.macd_text_view);
  }
}
