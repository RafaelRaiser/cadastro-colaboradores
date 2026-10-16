package com.rafael.colaboradores.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity//Esta classe representa uma entidade do banco de dados, ou seja, uma tabela
public class Colaborador {
    
    @Id //chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //geração automática do id
    private Long id;

    private String nome;
    private String cargo;
    private LocalDate dataAdmissao;//so a data, sem hora

    @ManyToOne //muitos colaboradores para um departamento
    @JoinColumn(name = "departamento_id") //nome da coluna que vai armazenar a chave estrangeira
    private Departamento departamento;

    public Colaborador() {
    }//o JPA exige um contrutor vazio

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
