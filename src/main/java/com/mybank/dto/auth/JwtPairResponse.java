package com.mybank.dto.auth;

public class JwtPairResponse {
    private String accessToken;
    private String refreshToken;

    public JwtPairResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }

}
