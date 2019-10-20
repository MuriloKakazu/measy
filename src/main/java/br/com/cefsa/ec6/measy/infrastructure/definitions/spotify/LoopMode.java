package br.com.cefsa.ec6.measy.infrastructure.definitions.spotify;

public enum LoopMode {
  LOOP_TRACK("track"),
  LOOP_CONTEXT("context"),
  LOOP_OFF("off");

  private final String state;

  LoopMode(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }
}
