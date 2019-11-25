package br.com.cefsa.ec6.measy.infrastructure.client.rest;

import br.com.cefsa.ec6.measy.infrastructure.browser.Browser;
import br.com.cefsa.ec6.measy.infrastructure.builder.JsonArrayBuilder;
import br.com.cefsa.ec6.measy.infrastructure.cache.Cache;
import br.com.cefsa.ec6.measy.infrastructure.definitions.spotify.LoopMode;
import br.com.cefsa.ec6.measy.infrastructure.exception.NavigationException;
import com.google.gson.JsonArray;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.IRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.lang.reflect.Array;
import java.util.Arrays;

@Component
public class SpotifyClient {

  @Autowired private Cache cache;
  @Autowired private SpotifyApi spotifyApi;

  private String scope;

  public SpotifyClient(@Value("${spotify.client.scope}") String scope) {
    this.scope = scope;
  }

  public void requestAuthToken() throws NavigationException {
    final AuthorizationCodeUriRequest authUriRequest =
        spotifyApi.authorizationCodeUri().scope(scope).show_dialog(false).build();

    Browser.navigateTo(authUriRequest.execute());
  }

  public void consumeAuthToken(@NotNull String authToken) {
    try {
      final AuthorizationCodeCredentials credentials =
          spotifyApi.authorizationCode(authToken).build().execute();

      cache.put("spotifyAuthToken", authToken);

      auth(credentials);
    } catch (Exception e) {
      throw new RestClientException("Authentication failed", e);
    }
  }

  public void refresh(@NotNull String refreshToken) {
    try {
      final AuthorizationCodeCredentials credentials =
          spotifyApi.authorizationCodeRefresh().build().execute();
      auth(credentials);
    } catch (Exception e) {
      throw new RestClientException("Token refresh failed", e);
    }
  }

  public void auth(@NotNull AuthorizationCodeCredentials credentials) {
    String accessToken = credentials.getAccessToken();
    String refreshToken = credentials.getRefreshToken();

    cache.put("spotifyAccessToken", accessToken);
    cache.put("spotifyRefreshToken", refreshToken);

    spotifyApi.setAccessToken(accessToken);
    spotifyApi.setRefreshToken(refreshToken);
  }

  private <T> T request(@NotNull IRequest<T> request) {
    try {
      return request.execute();
    } catch (Exception e) {
      throw new RestClientException("Request failed", e);
    }
  }

  public Track getTrack(@NotNull String trackId) {
    return request(spotifyApi.getTrack(trackId).build());
  }

  public Album getAlbum(@NotNull String albumId) {
    return request(spotifyApi.getAlbum(albumId).build());
  }

  public Paging<TrackSimplified> getAlbumTracks(@NotNull String albumId) {
    return request(spotifyApi.getAlbumsTracks(albumId).build());
  }

  public Playlist getPlaylist(@NotNull String playlistId) {
    return request(spotifyApi.getPlaylist(playlistId).build());
  }

  public Artist getArtist(@NotNull String artistId) {
    return request(spotifyApi.getArtist(artistId).build());
  }

  public Paging<AlbumSimplified> getArtistAlbums(@NotNull String artistId) {
    return request(spotifyApi.getArtistsAlbums(artistId).build());
  }

  public Track[] getArtistTopTracks(@NotNull String artistId, @NotNull CountryCode countryCode) {
    return request(spotifyApi.getArtistsTopTracks(artistId, countryCode).build());
  }

  public User getUser(@NotNull String userId) {
    return request(spotifyApi.getUsersProfile(userId).build());
  }

  public User getUser() {
    return request(spotifyApi.getCurrentUsersProfile().build());
  }

  public Paging<SavedAlbum> getUserSavedAlbums() {
    return request(spotifyApi.getCurrentUsersSavedAlbums().build());
  }

  public Paging<SavedTrack> getUserSavedTracks() {
    return request(spotifyApi.getUsersSavedTracks().build());
  }

  public Paging<PlaylistSimplified> getUserSavedPlaylists() {
    return request(spotifyApi.getListOfCurrentUsersPlaylists().build());
  }

  public Paging<Artist> getUserTopArtists() {
    return request(spotifyApi.getUsersTopArtists().build());
  }

  public Paging<Track> getUserTopTracks() {
    return request(spotifyApi.getUsersTopTracks().build());
  }

  public PagingCursorbased<PlayHistory> getUserPlayHistory() {
    return request(spotifyApi.getCurrentUsersRecentlyPlayedTracks().build());
  }

  public Paging<Track> searchTracks(@NotNull String trackName) {
    return request(spotifyApi.searchTracks(trackName).build());
  }

  public Paging<AlbumSimplified> searchAlbums(@NotNull String query) {
    return request(spotifyApi.searchAlbums(query).build());
  }

  public Paging<Artist> searchArtists(@NotNull String query) {
    return request(spotifyApi.searchArtists(query).build());
  }

  public Paging<PlaylistSimplified> searchPlaylists(@NotNull String query) {
    return request(spotifyApi.searchPlaylists(query).build());
  }

  public void playTrack(@NotNull String trackUri) {
    playTracks(trackUri);
  }

  public void playTracks(@NotNull String... trackUris) {
    JsonArray uris = new JsonArrayBuilder().with(trackUris).build();
    request(spotifyApi.startResumeUsersPlayback().uris(uris).position_ms(0).build());
  }

  public void playContext(@NotNull String contextUri) {
    request(spotifyApi.startResumeUsersPlayback().context_uri(contextUri).position_ms(0).build());
  }

  public void resumePlayback() {
    request(spotifyApi.startResumeUsersPlayback().build());
  }

  public void setShuffle(Boolean shuffle) {
    request(spotifyApi.toggleShuffleForUsersPlayback(shuffle).build());
  }

  public void setLoop(LoopMode loopMode) {
    request(spotifyApi.setRepeatModeOnUsersPlayback(loopMode.getState()).build());
  }

  public void pausePlayback() {
    request(spotifyApi.pauseUsersPlayback().build());
  }

  public void nextTrack() {
    request(spotifyApi.skipUsersPlaybackToNextTrack().build());
  }

  public void previousTrack() {
    request(spotifyApi.skipUsersPlaybackToPreviousTrack().build());
  }

  public void setVolume(@Min(0) @Max(100) Integer volumePercentage) {
    request(spotifyApi.setVolumeForUsersPlayback(volumePercentage).build());
  }

  public CurrentlyPlaying getCurrentTrack() {
    return request(spotifyApi.getUsersCurrentlyPlayingTrack().build());
  }

  public CurrentlyPlayingContext getPlaybackInfo() {
    return request(spotifyApi.getInformationAboutUsersCurrentPlayback().build());
  }

  public void addTrackToPlaylist(@NotNull String playlistId, @NotNull String trackUri) {
    request(spotifyApi.addTracksToPlaylist(playlistId, new JsonArrayBuilder().with(trackUri).build()).build());
  }
}
