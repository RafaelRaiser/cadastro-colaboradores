package com.rafael.colaboradores.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.colaboradores.model.Departamento;
import com.rafael.colaboradores.repository.DepartamentoRepository;

@RestController                       // esta classe responde HTTP com JSON
@RequestMapping("/api/departamentos") // endereco base de todos os metodos
public class DepartamentoController {

    private final DepartamentoRepository repository;

    public DepartamentoController(DepartamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping // GET /api/departamentos
    public List<Departamento> listar() {
        return repository.findAll();
    }
}