package com.example.koina.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.koina.chat.model.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, String>, JpaSpecificationExecutor<Chat>{

}
