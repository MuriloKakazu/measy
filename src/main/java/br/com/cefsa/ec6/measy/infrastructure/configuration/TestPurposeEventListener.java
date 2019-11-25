package br.com.cefsa.ec6.measy.infrastructure.configuration;

import br.com.cefsa.ec6.measy.infrastructure.client.rest.MusixmatchClient;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.YoutubeClient;
import br.com.cefsa.ec6.measy.infrastructure.exception.NavigationException;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Collection;

@Configuration
public class TestPurposeEventListener {

  @Autowired MusixmatchClient musixmatchClient;

  @Autowired SpotifyClient spotifyClient;

  @Autowired YoutubeClient youtubeClient;

  @EventListener
  public void onRefresh(final ApplicationReadyEvent event) {
//    try {
//      spotifyClient.requestAuthToken();
//    } catch (NavigationException e) {
//      e.printStackTrace();
//    }
  }
}
