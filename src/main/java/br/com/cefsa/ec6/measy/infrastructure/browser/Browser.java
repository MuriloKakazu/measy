package br.com.cefsa.ec6.measy.infrastructure.browser;

import br.com.cefsa.ec6.measy.infrastructure.exception.NavigationException;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/** Can navigate to either a local or web resource. */
public class Browser {
  public static void navigateTo(URI uri) throws NavigationException {
    try {
      if (Desktop.isDesktopSupported()) {
        // Windows
        navigateWithDesktop(uri);
      } else {
        // UNIX
        navigateWithUNIX(uri);
      }
    } catch (IOException e) {
      throw new NavigationException(e);
    }
  }

  private static void navigateWithDesktop(URI uri) throws IOException {
    Desktop desktop = Desktop.getDesktop();
    desktop.browse(uri);
  }

  private static void navigateWithUNIX(URI uri) throws IOException {
    Runtime runtime = Runtime.getRuntime();
    runtime.exec("xdg-open " + uri);
  }
}
