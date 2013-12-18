package com.truongtho.macd;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import com.truongtho.macd.cache.HashMapCache;
import com.truongtho.macd.cache.KeyValueCache;
import com.truongtho.macd.cache.LruCacheMap;
import java.util.LinkedList;
import java.util.List;

/** Created with IntelliJ IDEA. User: tho Date: 9/17/13 Time: 11:30 AM */
public class MacdTextParser {
  private KeyValueCache<String, Spanned> cache;
  private String original;
  private SpannableStringBuilder richText;
  private List<MacdTag> tags;
  private boolean useBuiltInTags;

  //<editor-fold desc="Constructors">
  public MacdTextParser() {
    this(true);
  }

  public MacdTextParser(boolean useBuiltInTags) {
    this.useBuiltInTags = useBuiltInTags;
    this.cache = defaultCache();

    if (useBuiltInTags) {
      MacdTag[] builtInTags = getBuildInProcessors();
      for (MacdTag processor : builtInTags) {
        register(processor);
      }
    }
  }

  private KeyValueCache<String, Spanned> defaultCache() {
    KeyValueCache<String, Spanned> cacheInstance;
    if (hasLruCacheOnClassPath()) {
      cacheInstance = LruCacheInstantiator.instantiate();
    } else {
      cacheInstance = new HashMapCache<String, Spanned>();
    }
    return cacheInstance;
  }

  private boolean hasLruCacheOnClassPath() {
    try {
      Class.forName("android.util.LruCache");
      return true;
    } catch (ClassNotFoundException ex) {
      return false;
    }
  }
  //</editor-fold>

  private MacdTag[] getBuildInProcessors() {
    return new MacdTag[] {
        new BoldTag(), new ItalicTag(), new LinkTag(), new ColorTag()
    };
  }

  public void register(MacdTag processor) {
    if (processor == null) {
      throw new IllegalArgumentException("processor cannot be null");
    }

    if (tags == null) {
      tags = new LinkedList<MacdTag>();
    }

    tags.add(processor);
  }

  public MacdTextParser load(CharSequence text) {
    return load(text.toString());
  }

  public MacdTextParser load(String text) {
    setText(text);
    return this;
  }

  private void setText(String text) {
    this.original = text;
    richText = new SpannableStringBuilder(text);
  }

  public Spanned parse() {
    if (!needProcess()) {
      return richText;
    }

    String cachedKey = getCachedText();
    Spanned cachedText = cache.get(cachedKey);

    if (cachedText != null) {
      return cachedText;
    }

    for (MacdTag processor : tags) {
      richText = processor.process(richText);
    }

    cache.put(cachedKey, richText);
    return richText;
  }

  private boolean needProcess() {
    return tags != null && !tags.isEmpty();
  }

  private String getCachedText() {
    StringBuilder cachedKey = new StringBuilder();
    cachedKey.append(richText);
    for (MacdTag processor : tags) {
      cachedKey.append('_').append(processor.key());
    }
    return cachedKey.toString();
  }

  public String getOriginal() {
    return original;
  }

  public boolean isUseBuiltInTags() {
    return useBuiltInTags;
  }

  public static MacdTextParser getInstance() {
    if (sInstance == null) {
      sInstance = new MacdTextParser();
    }

    return sInstance;
  }

  private static MacdTextParser sInstance;

  /**
   * Indirection for LruCache class to prevent VerifyErrors on Android 2.0 and earlier when the
   * dependency is not present.
   */
  private static class LruCacheInstantiator {
    static KeyValueCache<String, Spanned> instantiate() {
      return new LruCacheMap<String, Spanned>();
    }
  }
}
