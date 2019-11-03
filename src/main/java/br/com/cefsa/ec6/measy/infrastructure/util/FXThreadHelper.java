package br.com.cefsa.ec6.measy.infrastructure.util;

import javafx.application.Platform;

public class FXThreadHelper {

  public static void run(Runnable runnable) {
    Platform.runLater(
        () -> {
          runnable.run();
        });
  }
}
