package br.com.cefsa.ec6.measy.infrastructure.util.formatter;

import com.wrapper.spotify.model_objects.specification.Track;
import java.util.Arrays;

public class TrackFormatter {

  public static String formatTrackTitle(Track track) {
    return track.getName();
  }

  public static String formatTrackArtistAndTitle(Track track) {
    return String.format(
        "%s - %s",
        ArtistFormatter.formatSimplifiedMainArtistName(Arrays.asList(track.getArtists())),
        track.getName());
  }
}
