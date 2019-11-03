package br.com.cefsa.ec6.measy.infrastructure.util;

import com.wrapper.spotify.model_objects.specification.Image;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ImagePicker {

  public static Image pickHighestResolutionImage(Collection<Image> images) {
    return Collections.max(images, Comparator.comparing(image -> image.getWidth()));
  }

  public static Image pickLowestResolutionImage(Collection<Image> images) {
    return Collections.min(images, Comparator.comparing(image -> image.getWidth()));
  }
}
