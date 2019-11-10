package br.com.cefsa.ec6.measy.infrastructure.util;

import com.wrapper.spotify.model_objects.specification.Image;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ImagePicker {

  public static final Image PLACEHOLDER =
      new Image.Builder().setUrl("placeholders/no_media.jpg").setHeight(300).setWidth(300).build();

  public static Image pickHighestResolutionImage(Image[] images) {
    return pickHighestResolutionImage(Arrays.asList(images));
  }

  public static Image pickLowestResolutionImage(Image[] images) {
    return pickLowestResolutionImage(Arrays.asList(images));
  }

  public static Image pickHighestResolutionImage(Collection<Image> images) {
    if (images.isEmpty()) return PLACEHOLDER;
    return Collections.max(images, Comparator.comparing(image -> image.getWidth()));
  }

  public static Image pickLowestResolutionImage(Collection<Image> images) {
    if (images.isEmpty()) return PLACEHOLDER;
    return Collections.min(images, Comparator.comparing(image -> image.getWidth()));
  }
}
