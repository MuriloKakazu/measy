package br.com.cefsa.ec6.measy.infrastructure.util;

import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Track;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpotifyUriHelper {

  public static Boolean isTrackUri(@NotNull String uri) {
    return isOfType(uri, Track.class);
  }

  private static Boolean isOfType(@NotNull String uri, @NotNull Class clazz) {
    return getTypeName(uri).equals(clazz.getSimpleName().toLowerCase());
  }

  public static String getId(@NotNull String uri) {
    return uri.split(":")[2];
  }

  public static String getTypeName(@NotNull String uri) {
    return uri.split(":")[1];
  }

  public static ModelObjectType getType(@NotNull String uri) {
    return ModelObjectType.keyOf(getTypeName(uri));
  }
}
