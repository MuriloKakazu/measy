package br.com.cefsa.ec6.measy.data.builder.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.Track;
import br.com.cefsa.ec6.measy.infrastructure.builder.Builder;

public class TrackBuilder implements Builder<Track> {
  private Track track = new Track();

  public TrackBuilder withId(String id) {
    track.setId(id);
    return this;
  }

  public TrackBuilder withName(String name) {
    track.setName(name);
    return this;
  }

  @Override
  public Track build() {
    return track;
  }
}
