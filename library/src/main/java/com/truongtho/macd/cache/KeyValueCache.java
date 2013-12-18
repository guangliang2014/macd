package com.truongtho.macd.cache;

/**
 * Created with IntelliJ IDEA.
 * User: nguyentruongtho.sg@gmail.com
 * Date: 12/18/13
 * Time: 9:12 PM
 * Copyright @ the company that the author works for <3
 */
public interface KeyValueCache<K, V> {
  V get(K key);
  V put(K key, V value);
}
