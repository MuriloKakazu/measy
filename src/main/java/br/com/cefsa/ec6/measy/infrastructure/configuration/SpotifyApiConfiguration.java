package br.com.cefsa.ec6.measy.infrastructure.configuration;

import com.wrapper.spotify.SpotifyApi;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyApiConfiguration {

  @Bean
  public SpotifyApi spotifyApi(
      @Value("${spotify.client.id}") String clientId,
      @Value("${spotify.client.secret}") String clientSecret,
      @Value("${spotify.client.redirect-uri}") String redirectUri) {

    return SpotifyApi.builder()
        .setClientId(clientId)
        .setClientSecret(clientSecret)
        .setRedirectUri(URI.create(redirectUri))
        .build();
  }
}
