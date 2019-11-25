package br.com.cefsa.ec6.measy.infrastructure.util.formatter;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class YoutubeUrlFormatter {

  @Value("${youtube.client.settings.embedded-url}")
  private String embeddedVideoUrlBase;

  public String getVideoUrlFromId(@NotNull String videoId) {
    return embeddedVideoUrlBase.concat(videoId).concat("?modestbranding=1&showinfo=0&fs=0");
  }
}
