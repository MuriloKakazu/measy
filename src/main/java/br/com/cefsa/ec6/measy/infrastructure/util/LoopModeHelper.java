package br.com.cefsa.ec6.measy.infrastructure.util;

import br.com.cefsa.ec6.measy.application.enums.Icon;
import br.com.cefsa.ec6.measy.infrastructure.definitions.spotify.LoopMode;

public class LoopModeHelper {

  public static LoopMode getNextLoopMode(LoopMode loopMode) {
    switch (loopMode) {
      case LOOP_OFF:
        return LoopMode.LOOP_CONTEXT;
      case LOOP_CONTEXT:
        return LoopMode.LOOP_TRACK;
      case LOOP_TRACK:
        return LoopMode.LOOP_OFF;
    }
    return LoopMode.LOOP_OFF;
  }

  public static Icon getIcon(LoopMode loopMode) {
    switch (loopMode) {
      case LOOP_CONTEXT:
      case LOOP_OFF:
        return Icon.REPEAT_WHITE;
      case LOOP_TRACK:
        return Icon.REPEAT_ONE_WHITE;
    }
    return Icon.REPEAT_WHITE;
  }
}
