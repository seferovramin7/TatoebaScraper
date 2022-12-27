package com.example.tatoebascraper.dto.tatoeba;

import lombok.Data;

@Data
class User {
    public String username;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
