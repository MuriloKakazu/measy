package br.com.cefsa.ec6.measy.domain.model.fixtures;

import br.com.cefsa.ec6.measy.infrastructure.builder.RuleBuilder;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.wrapper.spotify.model_objects.specification.Image;

public class ImageFixtures implements TemplateLoader {

  @Override
  public void load() {

    final String imageUrl =
        "https://i.pinimg.com/564x/90/a5/4e/90a54e7aa8c8b8e813f90f2e3f7dd3a7.jpg";

    Fixture.of(Image.class)
        .addTemplate(
            "small",
            new RuleBuilder().with("url", imageUrl).with("width", 150).with("height", 150).build())
        .addTemplate(
            "medium",
            new RuleBuilder().with("url", imageUrl).with("width", 250).with("height", 250).build())
        .addTemplate(
            "large",
            new RuleBuilder().with("url", imageUrl).with("width", 500).with("height", 500).build());
  }
}
