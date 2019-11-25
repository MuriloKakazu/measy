package br.com.cefsa.ec6.measy.application.controller.rest.handler;

import br.com.cefsa.ec6.measy.application.event.SpotifyAuthEvent;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import br.com.cefsa.ec6.measy.infrastructure.util.AlertUtil;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotifyAuthCallbackRestControllerHandler {

  @Autowired private EventBus eventBus;
  @Autowired private SpotifyClient spotifyClient;

  /*
   Handle OAuth callback from Spotify
  */
  public String handleCallback(String code, String state, String error) {
    Boolean hasCode = !code.isBlank();
    Boolean hasError = !error.isBlank();

    if (!hasCode) {
      String message = "Authentication was not successful!";
      AlertUtil.showErrorAsync(message);
      return message;
    }

    if (hasError) {
      String message = String.format("Authentication failed: %s", error);
      AlertUtil.showErrorAsync(message);
      return message;
    }

    try {

      spotifyClient.consumeAuthToken(code);
      eventBus.post(new SpotifyAuthEvent());
      return "Authentication successful!";

    } catch (Exception e) {
      String message = String.format("An unexpected error has occurred: %s", e.getMessage());
      AlertUtil.showErrorAsync(message);
      return message;
    }
  }
}
