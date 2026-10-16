package com.rafael.colaboradores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.colaboradores.model.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    // o Spring monta a consulta a partir do NOME do metodo
    List<Colaborador> findByCargoContainingIgnoreCase(String cargo);
}