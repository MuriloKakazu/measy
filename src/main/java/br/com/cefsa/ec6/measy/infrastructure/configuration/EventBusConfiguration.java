package br.com.cefsa.ec6.measy.infrastructure.configuration;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.EventListener;
import java.util.UUID;

@Configuration
public class EventBusConfiguration {

    @Bean
    public EventBus eventBus(Collection<EventListener> eventListeners) {
        EventBus eventBus = new EventBus(UUID.randomUUID().toString());

        eventListeners.forEach(listener -> eventBus.register(listener));

        return eventBus;
    }

}
