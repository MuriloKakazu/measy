package br.com.cefsa.ec6.measy.infrastructure.util.formatter;

import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import java.util.Collection;
import java.util.stream.Collectors;

public class ArtistFormatter {

  public static String formatArtistsNames(Collection<Artist> artists) {
    return artists.stream().map(artist -> artist.getName()).collect(Collectors.joining(", "));
  }

  public static String formatSimplifiedArtistsNames(Collection<ArtistSimplified> artists) {
    return artists.stream().map(artist -> artist.getName()).collect(Collectors.joining(", "));
  }

  public static String formatMainArtistName(Collection<Artist> artists) {
    return artists.stream().findFirst().orElse(new Artist.Builder().build()).getName();
  }

  public static String formatSimplifiedMainArtistName(Collection<ArtistSimplified> artists) {
    return artists.stream().findFirst().orElse(new ArtistSimplified.Builder().build()).getName();
  }
}
