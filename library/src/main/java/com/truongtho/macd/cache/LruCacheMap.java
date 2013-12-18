package com.truongtho.macd.cache;

import android.util.LruCache;

/**
 * Created with IntelliJ IDEA.
 * User: nguyentruongtho.sg@gmail.com
 * Date: 12/18/13
 * Time: 8:46 PM
 * Copyright @ the company that the author works for <3
 */
public class LruCacheMap<K, V> implements KeyValueCache<K, V> {
  private final LruCache<K, V> lruCache;

  public LruCacheMap() {
    this.lruCache = new LruCache<K, V>(200);
  }

  @Override public V get(K key) {
    return lruCache.get(key);
  }

  @Override public V put(K key, V value) {
    return lruCache.put(key, value);
  }
}
