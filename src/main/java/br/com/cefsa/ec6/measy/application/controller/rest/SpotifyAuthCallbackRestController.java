package br.com.cefsa.ec6.measy.application.controller.rest;

import br.com.cefsa.ec6.measy.application.controller.rest.handler.SpotifyAuthCallbackRestControllerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotifyAuthCallbackRestController {

  @Autowired private SpotifyAuthCallbackRestControllerHandler handler;

  @RequestMapping("/callback")
  public String callbackResource(
      @RequestParam(value = "code", defaultValue = "") String code,
      @RequestParam(value = "state", defaultValue = "") String state,
      @RequestParam(value = "error", defaultValue = "") String error) {

    return handler.handleCallback(code, state, error);
  }
}
