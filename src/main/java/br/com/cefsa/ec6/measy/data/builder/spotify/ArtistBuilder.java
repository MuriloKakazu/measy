package br.com.cefsa.ec6.measy.data.builder.spotify;

import br.com.cefsa.ec6.measy.data.builder.Builder;
import br.com.cefsa.ec6.measy.domain.model.spotify.Artist;

public class ArtistBuilder implements Builder<Artist> {
  private Artist artist = new Artist();

  @Override
  public Artist build() {
    return artist;
  }
}
