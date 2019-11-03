package br.com.cefsa.ec6.measy.infrastructure.util;

import br.com.cefsa.ec6.measy.application.enums.Icon;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.PathFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IconURLFinder {

  private String relativePath;

  public IconURLFinder(@Value("${application.resources.icons.relative-path}") String relativePath) {
    this.relativePath = relativePath;
  }

  public String findRelativePath(Icon icon) {
    return PathFormatter.format(relativePath, icon.type);
  }
}
