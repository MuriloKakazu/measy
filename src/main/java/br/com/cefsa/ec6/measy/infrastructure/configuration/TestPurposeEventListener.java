package br.com.cefsa.ec6.measy.infrastructure.configuration;

import br.com.cefsa.ec6.measy.data.client.MusixmatchClient;
import br.com.cefsa.ec6.measy.data.client.SpotifyClient;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class TestPurposeEventListener {

  @Autowired MusixmatchClient musixmatchClient;

  @Autowired SpotifyClient spotifyClient;

  @EventListener
  public void onRefresh(final ApplicationReadyEvent event) {
    try {
      Track track = musixmatchClient.getTrack("No Air", "Jordin Sparks");
      Lyrics lyrics = musixmatchClient.getLyrics(track.getTrack().getTrackId());

      System.out.println();
    } catch (MusixMatchException e) {
      e.printStackTrace();
    }
  }
}
