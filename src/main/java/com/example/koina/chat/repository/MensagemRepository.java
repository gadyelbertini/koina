package com.example.koina.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.koina.chat.model.entity.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, String>, JpaSpecificationExecutor<Mensagem>{

}