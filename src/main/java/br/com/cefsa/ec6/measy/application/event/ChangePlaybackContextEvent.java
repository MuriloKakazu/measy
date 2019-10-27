package br.com.cefsa.ec6.measy.application.event;

public class ChangePlaybackContextEvent {

  private String contextUri;

  public ChangePlaybackContextEvent(String contextUri) {
    setContextUri(contextUri);
  }

  public String getContextUri() {
    return contextUri;
  }

  public void setContextUri(String contextUri) {
    this.contextUri = contextUri;
  }
}
