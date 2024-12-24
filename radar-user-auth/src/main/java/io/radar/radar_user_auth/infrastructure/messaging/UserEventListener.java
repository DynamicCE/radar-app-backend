package io.radar.radar_user_auth.infrastructure.messaging;

import io.radar.radar_user_auth.infrastructure.messaging.UserEventPublisher.UserRegisteredEvent;
import io.radar.radar_user_auth.infrastructure.messaging.UserEventPublisher.UserActivatedEvent;
import io.radar.radar_user_auth.infrastructure.messaging.UserEventPublisher.UserDeactivatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserEventListener {

    @Async("taskExecutor")
    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("Kullanıcı kayıt eventi işleniyor: {}", event.user().getUsername());
    }

    @Async("taskExecutor")
    @EventListener
    public void handleUserActivatedEvent(UserActivatedEvent event) {
        log.info("Kullanıcı aktivasyon eventi işleniyor: {}", event.user().getUsername());
    }

    @Async("taskExecutor")
    @EventListener
    public void handleUserDeactivatedEvent(UserDeactivatedEvent event) {
        log.info("Kullanıcı deaktivasyon eventi işleniyor: {}", event.user().getUsername());
    }
}