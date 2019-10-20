package br.com.cefsa.ec6.measy.data.builder.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.Playlist;
import br.com.cefsa.ec6.measy.infrastructure.builder.Builder;

public class PlaylistBuilder implements Builder<Playlist> {
  private Playlist playlist = new Playlist();

  @Override
  public Playlist build() {
    return playlist;
  }
}
