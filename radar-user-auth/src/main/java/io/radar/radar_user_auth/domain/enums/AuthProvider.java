package io.radar.radar_user_auth.domain.enums;

public enum AuthProvider {
    LOCAL,
    GOOGLE;

    public String getProvider() {
        return this.name().toLowerCase();
    }
}