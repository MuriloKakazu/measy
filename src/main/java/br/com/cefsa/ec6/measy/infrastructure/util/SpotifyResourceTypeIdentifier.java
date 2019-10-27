package br.com.cefsa.ec6.measy.infrastructure.util;

import com.wrapper.spotify.model_objects.specification.Track;
import javax.validation.constraints.NotNull;

public class SpotifyResourceTypeIdentifier {

  public static Boolean isTrackUri(@NotNull String uri) {
    return isOfType(uri, Track.class);
  }

  private static Boolean isOfType(@NotNull String uri, @NotNull Class clazz) {
    return uri.startsWith(String.format("spotify:%s", clazz.getSimpleName().toLowerCase()));
  }
}
