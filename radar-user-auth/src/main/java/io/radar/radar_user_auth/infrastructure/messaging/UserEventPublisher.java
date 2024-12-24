package io.radar.radar_user_auth.infrastructure.messaging;

import io.radar.radar_user_auth.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishUserRegistered(User user) {
        UserRegisteredEvent event = new UserRegisteredEvent(user);
        log.info("Yayınlanan kullanıcı kayıt eventi: {}", user.getUsername());
        eventPublisher.publishEvent(event);
    }

    public void publishUserActivated(User user) {
        UserActivatedEvent event = new UserActivatedEvent(user);
        log.info("Yayınlanan kullanıcı aktivasyon eventi: {}", user.getUsername());
        eventPublisher.publishEvent(event);
    }

    public void publishUserDeactivated(User user) {
        UserDeactivatedEvent event = new UserDeactivatedEvent(user);
        log.info("Yayınlanan kullanıcı deaktivasyon eventi: {}", user.getUsername());
        eventPublisher.publishEvent(event);
    }

    record UserRegisteredEvent(User user) {
    }

    record UserActivatedEvent(User user) {
    }

    record UserDeactivatedEvent(User user) {
    }
}