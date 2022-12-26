package com.example.tatoebascraper.repository;

import com.example.tatoebascraper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);

}
