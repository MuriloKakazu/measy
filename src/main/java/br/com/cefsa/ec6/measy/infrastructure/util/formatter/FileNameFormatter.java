package br.com.cefsa.ec6.measy.infrastructure.util.formatter;

public class FileNameFormatter {

  public static String withoutExtension(String fileName) {
    return fileName.replaceAll("\\..+", "");
  }
}
