package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

public class SearchCriteria {
    @NotBlank(message = "user cannot empty")
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
