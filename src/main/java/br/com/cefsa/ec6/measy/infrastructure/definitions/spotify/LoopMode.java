package br.com.cefsa.ec6.measy.infrastructure.definitions.spotify;

import java.util.HashMap;
import java.util.Map;

public enum LoopMode {
  LOOP_TRACK("track"),
  LOOP_CONTEXT("context"),
  LOOP_OFF("off");

  private static final Map<String, LoopMode> MAP = new HashMap<>();

  static {
    for (LoopMode loopMode : LoopMode.values()) {
      MAP.put(loopMode.getState(), loopMode);
    }
  }

  private final String state;

  LoopMode(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

  public static LoopMode getByKey(String key) {
    return MAP.get(key);
  }
}
