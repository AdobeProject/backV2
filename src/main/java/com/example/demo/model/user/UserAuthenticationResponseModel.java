package com.example.demo.model.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserAuthenticationResponseModel {

    private String token;

    private UserDetailsResponseModel userDetails;

    public UserAuthenticationResponseModel() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("token", token)
            .append("userDetails", userDetails)
            .toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public UserDetailsResponseModel getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(final UserDetailsResponseModel userDetails) {
        this.userDetails = userDetails;
    }
}
