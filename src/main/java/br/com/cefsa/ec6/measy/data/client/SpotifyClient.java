package br.com.cefsa.ec6.measy.data.client;

import br.com.cefsa.ec6.measy.infrastructure.browser.Browser;
import br.com.cefsa.ec6.measy.infrastructure.exception.NavigationException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import java.io.IOException;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpotifyClient {

  @Autowired private SpotifyApi spotifyApi;

  private String scope;
  private String accessToken;
  private String refreshToken;

  public SpotifyClient(@Value("${spotify.client.scope}") String scope) {
    this.scope = scope;
  }

  private String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  private String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
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
    auth(credentials);
  }

  public void refresh(@NotNull String refreshToken) throws IOException, SpotifyWebApiException {
    final AuthorizationCodeCredentials credentials =
        spotifyApi.authorizationCodeRefresh().build().execute();
    auth(credentials);
  }

  public void auth(@NotNull AuthorizationCodeCredentials credentials) {
    spotifyApi.setAccessToken(credentials.getAccessToken());
    spotifyApi.setRefreshToken(credentials.getRefreshToken());
  }
}
