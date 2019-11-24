package br.com.cefsa.ec6.measy.infrastructure.configuration;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YouTubeConfiguration {

  @Autowired private Engine browserEngine;
  @Autowired private Browser browser;
  @Autowired private BrowserView browserView;

  @Bean public YouTube youtubeApi() {

    final HttpTransport httpTransport = new NetHttpTransport();
    final JsonFactory jsonFactory = new JacksonFactory();
    final HttpRequestInitializer httpRequestInitializer = initializer -> { };

    return new YouTube.Builder(httpTransport, jsonFactory, httpRequestInitializer)
        .setApplicationName("measy")
        .build();
  }

  @Bean public Engine browserEngine(@Value("${jx-browser.auth.license-key}") String jxLicenseKey) {
    return Engine.newInstance(
        EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
            .licenseKey(jxLicenseKey)
            .build());
  }

  @Bean public Browser browser() {
    return browserEngine.newBrowser();
  }

  @Bean public BrowserView browserView() {
    return BrowserView.newInstance(browser);
  }

}
