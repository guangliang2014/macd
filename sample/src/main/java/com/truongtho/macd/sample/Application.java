package com.truongtho.macd.sample;

import android.content.Context;

/**
 * Created with IntelliJ IDEA.
 * User: nguyentruongtho.sg@gmail.com
 * Date: 11/10/13
 * Time: 11:47 AM
 * Copyright @ the company that the author works for <3
 */
public class Application extends android.app.Application {
  private static Context context;

  @Override public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
  }

  public static Context context() {
    return context;
  }
}
