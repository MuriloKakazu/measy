package br.com.cefsa.ec6.measy.domain.repository.musixmatch;

import br.com.cefsa.ec6.measy.infrastructure.client.rest.MusixmatchClient;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LyricsRepository {

  @Autowired private MusixmatchClient musixmatchClient;

  public Lyrics getLyricsFromTrack(String trackName, String tackArtistName) {
    try {

      Track track = musixmatchClient.getTrack(trackName, tackArtistName);
      return musixmatchClient.getLyrics(track.getTrack().getTrackId());

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
