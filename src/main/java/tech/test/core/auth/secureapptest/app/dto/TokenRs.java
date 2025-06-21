package tech.test.core.auth.secureapptest.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenRs(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("expires_in")
        long expiresIn,
        @JsonProperty("refresh_token")
        String refreshToken,
        @JsonProperty("refresh_expires_in")
        long refreshExpiresIn
) {
}
