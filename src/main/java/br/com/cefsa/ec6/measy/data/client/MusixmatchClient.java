package br.com.cefsa.ec6.measy.data.client;

import java.util.Collection;
import javax.validation.constraints.NotNull;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MusixmatchClient {

  @Autowired private MusixMatch musixmatchApi;

  private Integer defaultPageSize;

  public MusixmatchClient(
      @Value("${musixmatch.client.settings.default-page-size}") Integer pageSize) {
    defaultPageSize = pageSize;
  }

  public Track getTrack(@NotNull String trackName, @NotNull String artistName)
      throws MusixMatchException {
    return musixmatchApi.getMatchingTrack(trackName, artistName);
  }

  public Track getTrack(@NotNull String trackId) throws MusixMatchException {
    return getTrack(Integer.parseInt(trackId));
  }

  public Track getTrack(@NotNull Integer trackId) throws MusixMatchException {
    return musixmatchApi.getTrack(trackId);
  }

  public Lyrics getLyrics(@NotNull String trackId) throws MusixMatchException {
    return getLyrics(Integer.parseInt(trackId));
  }

  public Lyrics getLyrics(@NotNull Integer trackId) throws MusixMatchException {
    return musixmatchApi.getLyrics(trackId);
  }

  public Collection<Track> searchAny(String query) throws MusixMatchException {
    return search(query, "", "");
  }

  public Collection<Track> searchAny(
      String query, Integer page, Integer pageSize, Boolean hasLyrics) throws MusixMatchException {
    return search(query, "", "", page, pageSize, hasLyrics);
  }

  public Collection<Track> searchTracks(String trackName) throws MusixMatchException {
    return search("", "", trackName);
  }

  public Collection<Track> searchTracks(
      String trackName, Integer page, Integer pageSize, Boolean hasLyrics)
      throws MusixMatchException {
    return search("", "", trackName, page, pageSize, hasLyrics);
  }

  public Collection<Track> searchTracksByArtist(String artistName) throws MusixMatchException {
    return search("", artistName, "");
  }

  public Collection<Track> searchTracksByArtist(
      String artistName, Integer page, Integer pageSize, Boolean hasLyrics)
      throws MusixMatchException {
    return search("", artistName, "", page, pageSize, hasLyrics);
  }

  private Collection<Track> search(String query, String artistName, String trackName)
      throws MusixMatchException {
    return search(query, artistName, trackName, 1, defaultPageSize, true);
  }

  public Collection<Track> search(
      String query,
      String artistName,
      String trackName,
      @NotNull Integer page,
      @NotNull Integer pageSize,
      @NotNull Boolean hasLyrics)
      throws MusixMatchException {
    return musixmatchApi.searchTracks(query, artistName, trackName, page, pageSize, hasLyrics);
  }
}
