package com.rafael.colaboradores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity//Esta classe representa uma entidade do banco de dados, ou seja, uma tabela
public class Departamento {
    @Id //chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //geração automática do id
    private Long id;

    private String nome;

    //o JPA exige um contrutor vazio
    public Departamento() {
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

}
