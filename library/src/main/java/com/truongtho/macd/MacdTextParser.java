package com.truongtho.macd;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** Created with IntelliJ IDEA. User: tho Date: 9/17/13 Time: 11:30 AM */
public class MacdTextParser {
  private static Map<String, Spanned> cachedTexts = new HashMap<String, Spanned>();
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

    if (useBuiltInTags) {
      MacdTag[] builtInTags = getBuildInProcessors();
      for (MacdTag processor : builtInTags) {
        register(processor);
      }
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
    Spanned cachedText = cachedTexts.get(cachedKey);

    if (cachedText != null) {
      return cachedText;
    }

    for (MacdTag processor : tags) {
      richText = processor.process(richText);
    }

    cachedTexts.put(cachedKey, richText);
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
}
