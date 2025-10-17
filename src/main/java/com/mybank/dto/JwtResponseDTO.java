package com.mybank.dto;

public class JwtResponseDTO {
    //raspunsul cu token
    private String token;
    private String role;

    public JwtResponseDTO(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getRole() { return role; }
    public void setToken(String token) { this.token = token; }
}
