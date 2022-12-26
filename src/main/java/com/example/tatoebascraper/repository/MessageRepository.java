package com.example.tatoebascraper.repository;

import com.example.tatoebascraper.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
