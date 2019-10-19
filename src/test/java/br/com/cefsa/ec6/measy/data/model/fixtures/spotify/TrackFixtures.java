package br.com.cefsa.ec6.measy.data.model.fixtures.spotify;

import br.com.cefsa.ec6.measy.data.builder.RuleBuilder;
import br.com.cefsa.ec6.measy.domain.model.spotify.Track;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TrackFixtures implements TemplateLoader {
  @Override
  public void load() {
    Fixture.of(Track.class)
        .addTemplate(
            "sample-valid",
            new RuleBuilder()
                .with("id", "spotify:track:ffffffff")
                .with("name", "Sample Track")
                .build());
  }
}
