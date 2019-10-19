package br.com.cefsa.ec6.measy.infrastructure.exception;

public class NavigationException extends Exception {
  public NavigationException(Exception e) {
    super(e);
  }

  public NavigationException(String message) {
    super(message);
  }
}
