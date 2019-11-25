package br.com.cefsa.ec6.measy.infrastructure.util;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class TrackPicker {

  public static Collection<Track> fromPlaylistTracks(Paging<PlaylistTrack> playlistTracks) {
    return Arrays.stream(playlistTracks.getItems())
        .map(playlistTrack -> playlistTrack.getTrack())
        .collect(Collectors.toList());
  }
}
