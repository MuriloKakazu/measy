package br.com.cefsa.ec6.measy.application.controller.rest.handler;

import br.com.cefsa.ec6.measy.data.repository.musixmatch.LyricsRepository;
import br.com.cefsa.ec6.measy.data.repository.spotify.AlbumRepository;
import br.com.cefsa.ec6.measy.data.repository.spotify.ArtistRepository;
import br.com.cefsa.ec6.measy.data.repository.spotify.SpotifyRepository;
import br.com.cefsa.ec6.measy.data.repository.spotify.TrackRepository;
import br.com.cefsa.ec6.measy.domain.playback.SpotifyPlayer;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.MusixmatchClient;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class SpotifyAuthCallbackRestControllerHandler {

  @Autowired private SpotifyClient spotifyClient;

  /*
    Handle OAuth callback from Spotify
   */
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
