package br.com.cefsa.ec6.measy.domain.model.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Image;

public class ArtistFixtures implements TemplateLoader {

  @Override
  public void load() {

    Fixture.of(Artist.class)
        .addTemplate(
            "valid",
            new Rule() {
              {
                add("name", "Pogo");
                add("type", ModelObjectType.ARTIST);
                add("uri", "spotify:artist:1ng3xz2dyz57Z1WpnzM2G7");
                add("images", has(3).of(Image.class, "large", "medium", "small"));
              }
            });
  }
}
