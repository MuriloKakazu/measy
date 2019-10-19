package br.com.cefsa.ec6.measy.application.controller.rest.handler;

import br.com.cefsa.ec6.measy.data.client.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotifyAuthCallbackRestControllerHandler {

  @Autowired private SpotifyClient spotifyClient;

  public String handleCallback(String code, String state, String error) {
    Boolean hasCode = !code.isBlank();
    Boolean hasError = !error.isBlank();

    if (hasCode) {
      try {
        spotifyClient.consumeAuthToken(code);
      } catch (Exception e) {
        return String.format("An unexpected error has occurred: %s", e.getMessage());
      }
    }

    return hasError
        ? String.format("Authentication failed: %s", error)
        : String.format("Authentication successful!");
  }
}
