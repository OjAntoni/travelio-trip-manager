package com.example.traveliotripmanager.repository;

import com.example.traveliotripmanager.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
