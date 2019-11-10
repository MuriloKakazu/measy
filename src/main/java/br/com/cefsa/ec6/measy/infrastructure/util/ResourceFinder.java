package br.com.cefsa.ec6.measy.infrastructure.util;

import br.com.cefsa.ec6.measy.App;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ResourceFinder {

  public static URL fxml(String fxmlName) {
    return App.class.getResource("/fxml/" + fxmlName + ".fxml");
  }

  public static Collection<Resource> matching(String pattern) {
    try {

      final ResourcePatternResolver resolver =
          new PathMatchingResourcePatternResolver(App.class.getClassLoader());
      return Arrays.asList(resolver.getResources(pattern));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
