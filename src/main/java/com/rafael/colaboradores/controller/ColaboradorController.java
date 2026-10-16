package com.rafael.colaboradores.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.colaboradores.model.Colaborador;
import com.rafael.colaboradores.service.ColaboradorService;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    // GET /api/colaboradores           -> lista todos
    // GET /api/colaboradores?cargo=ana -> filtra por cargo
    @GetMapping
    public List<Colaborador> listar(@RequestParam(required = false) String cargo) {
        if (cargo != null && !cargo.isBlank()) {
            return service.buscarPorCargo(cargo);
        }
        return service.listar();
    }

    // GET /api/colaboradores/1
    @GetMapping("/{id}")
    public Colaborador buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // POST /api/colaboradores  (os dados vem no corpo, em JSON)
    @PostMapping
    public ResponseEntity<Colaborador> criar(@Valid @RequestBody Colaborador colaborador) {
        Colaborador salvo = service.salvar(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo); // 201
    }

    // PUT /api/colaboradores/1
    @PutMapping("/{id}")
    public Colaborador atualizar(@PathVariable Long id, @Valid @RequestBody Colaborador colaborador) {
        return service.atualizar(id, colaborador);
    }

    // DELETE /api/colaboradores/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build(); // 204
    }
}