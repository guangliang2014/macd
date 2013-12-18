package com.truongtho.macd.cache;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: nguyentruongtho.sg@gmail.com
 * Date: 12/18/13
 * Time: 9:15 PM
 * Copyright @ the company that the author works for <3
 */
public class HashMapCache<K, V> implements KeyValueCache<K, V> {

  private final HashMap<K, V> cache;

  public HashMapCache() {
    cache = new HashMap<K, V>();
  }

  @Override public V get(K key) {
    return cache.get(key);
  }

  @Override public V put(K key, V value) {
    return cache.put(key, value);
  }
}
