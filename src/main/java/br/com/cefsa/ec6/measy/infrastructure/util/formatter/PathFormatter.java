package br.com.cefsa.ec6.measy.infrastructure.util.formatter;

public class PathFormatter {

  public static String format(String path, String fileName) {
    return path.replace("{fileName}", fileName);
  }
}
