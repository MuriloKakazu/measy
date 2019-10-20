package br.com.cefsa.ec6.measy.infrastructure.configuration;

import org.jmusixmatch.MusixMatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusixmatchApiConfiguration {

  @Bean
  public MusixMatch musixmatchApi(@Value("${musixmatch.api.key}") String musixmatchApiKey) {

    return new MusixMatch(musixmatchApiKey);
  }
}
