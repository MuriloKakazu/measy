package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchBarController {}
