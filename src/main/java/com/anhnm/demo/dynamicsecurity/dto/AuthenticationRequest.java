package com.anhnm.demo.dynamicsecurity.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class AuthenticationRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
