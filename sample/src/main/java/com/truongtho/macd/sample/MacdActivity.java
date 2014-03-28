package com.truongtho.macd.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.truongtho.macd.MacdTextView;

public class MacdActivity extends Activity {

  private static final String TAG = MacdActivity.class.getName();

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    MacdTextView macdTextView = (MacdTextView) findViewById(R.id.macd_text_view);
    macdTextView.setOnElementClickListener(createOnElementClickListener());
  }

  private MacdTextView.OnElementClickListener createOnElementClickListener() {
    return new MacdTextView.OnElementClickListener() {
      @Override public void onClick(View textView, String data, String key, String[] matchStrings) {
        if (key.equals("link")) {
          String text = matchStrings[1];
          String url = matchStrings[2];
          Log.d(TAG, String.format("Clicked on %s, opening url %s", text, url));

          Intent i = new Intent(Intent.ACTION_VIEW);
          i.setData(Uri.parse(url));
          startActivity(i);
        } else {
          Log.d(TAG, "Element " + key + " has been clicked!");
        }
      }
    };
  }
}
