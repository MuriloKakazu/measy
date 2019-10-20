package br.com.cefsa.ec6.measy.data.client;

import br.com.cefsa.ec6.measy.infrastructure.browser.Browser;
import br.com.cefsa.ec6.measy.infrastructure.builder.JsonArrayBuilder;
import br.com.cefsa.ec6.measy.infrastructure.cache.Cache;
import br.com.cefsa.ec6.measy.infrastructure.definitions.spotify.LoopMode;
import br.com.cefsa.ec6.measy.infrastructure.exception.NavigationException;
import com.google.gson.JsonArray;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.IRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import java.io.IOException;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

  public void consumeAuthToken(@NotNull String authToken)
      throws IOException, SpotifyWebApiException {
    final AuthorizationCodeCredentials credentials =
        spotifyApi.authorizationCode(authToken).build().execute();

    cache.put("spotifyAuthToken", authToken);

    auth(credentials);
  }

  public void refresh(@NotNull String refreshToken) throws IOException, SpotifyWebApiException {
    final AuthorizationCodeCredentials credentials =
        spotifyApi.authorizationCodeRefresh().build().execute();
    auth(credentials);
  }

  public void auth(@NotNull AuthorizationCodeCredentials credentials) {
    String accessToken = credentials.getAccessToken();
    String refreshToken = credentials.getRefreshToken();

    cache.put("spotifyAccessToken", accessToken);
    cache.put("spotifyRefreshToken", refreshToken);

    spotifyApi.setAccessToken(accessToken);
    spotifyApi.setRefreshToken(refreshToken);
  }

  private <T> T request(@NotNull IRequest<T> request) throws IOException, SpotifyWebApiException {
    return request.execute();
  }

  public Track getTrack(@NotNull String trackId) throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getTrack(trackId).build());
  }

  public Album getAlbum(@NotNull String albumId) throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getAlbum(albumId).build());
  }

  public Playlist getPlaylist(@NotNull String playlistId)
      throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getPlaylist(playlistId).build());
  }

  public Artist getArtist(@NotNull String artistId) throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getArtist(artistId).build());
  }

  public User getUser(@NotNull String userId) throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getUsersProfile(userId).build());
  }

  public User getUser() throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getCurrentUsersProfile().build());
  }

  public Paging<SavedAlbum> getUserSavedAlbums() throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getCurrentUsersSavedAlbums().build());
  }

  public Paging<SavedTrack> getUserSavedTracks() throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getUsersSavedTracks().build());
  }

  public Paging<PlaylistSimplified> getUserSavedPlaylists()
      throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getListOfCurrentUsersPlaylists().build());
  }

  public Paging<Artist> getUserTopArtists() throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getUsersTopArtists().build());
  }

  public Paging<Track> getUserTopTracks() throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getUsersTopTracks().build());
  }

  public PagingCursorbased<PlayHistory> getUserPlayHistory()
      throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getCurrentUsersRecentlyPlayedTracks().build());
  }

  public Paging<Track> searchTracks(@NotNull String trackName)
      throws IOException, SpotifyWebApiException {
    return request(spotifyApi.searchTracks(trackName).build());
  }

  public Paging<AlbumSimplified> searchAlbums(@NotNull String albumName)
      throws IOException, SpotifyWebApiException {
    return request(spotifyApi.searchAlbums(albumName).build());
  }

  public Paging<Artist> searchArtists(@NotNull String artistName)
      throws IOException, SpotifyWebApiException {
    return request(spotifyApi.searchArtists(artistName).build());
  }

  public Paging<PlaylistSimplified> searchPlaylists(@NotNull String playlistName)
      throws IOException, SpotifyWebApiException {
    return request(spotifyApi.searchPlaylists(playlistName).build());
  }

  public void playTrack(@NotNull String trackId) throws IOException, SpotifyWebApiException {
    playTracks(trackId);
  }

  public void playTracks(@NotNull String... trackIds) throws IOException, SpotifyWebApiException {
    JsonArray uris = new JsonArrayBuilder().with(trackIds).build();

    request(spotifyApi.startResumeUsersPlayback().uris(uris).position_ms(0).build());
  }

  public void playContext(@NotNull String contextId) throws IOException, SpotifyWebApiException {
    request(spotifyApi.startResumeUsersPlayback().context_uri(contextId).position_ms(0).build());
  }

  public void resumePlayback() throws IOException, SpotifyWebApiException {
    request(spotifyApi.startResumeUsersPlayback().build());
  }

  public void setShuffle(Boolean shuffle) throws IOException, SpotifyWebApiException {
    request(spotifyApi.toggleShuffleForUsersPlayback(shuffle).build());
  }

  public void setLoop(LoopMode loopMode) throws IOException, SpotifyWebApiException {
    request(spotifyApi.setRepeatModeOnUsersPlayback(loopMode.getState()).build());
  }

  public void pausePlayback() throws IOException, SpotifyWebApiException {
    request(spotifyApi.pauseUsersPlayback().build());
  }

  public void nextTrack() throws IOException, SpotifyWebApiException {
    request(spotifyApi.skipUsersPlaybackToNextTrack().build());
  }

  public void previousTrack() throws IOException, SpotifyWebApiException {
    request(spotifyApi.skipUsersPlaybackToPreviousTrack().build());
  }

  public CurrentlyPlaying getCurrentTrack() throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getUsersCurrentlyPlayingTrack().build());
  }

  public CurrentlyPlayingContext getPlaybackInfo() throws IOException, SpotifyWebApiException {
    return request(spotifyApi.getInformationAboutUsersCurrentPlayback().build());
  }
}
