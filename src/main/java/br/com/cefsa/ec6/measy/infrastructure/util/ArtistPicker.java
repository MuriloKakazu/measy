package br.com.cefsa.ec6.measy.infrastructure.util;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import java.util.Arrays;

public class ArtistPicker {

  public static ArtistSimplified pickMain(ArtistSimplified... artists) {
    return Arrays.stream(artists).findFirst().orElse(null);
  }
}
