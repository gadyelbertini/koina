package com.example.koina.atendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.koina.atendimento.model.entity.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, String>, JpaSpecificationExecutor<Atendimento>{

}
