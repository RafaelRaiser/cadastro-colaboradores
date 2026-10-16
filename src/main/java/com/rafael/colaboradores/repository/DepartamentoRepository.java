package com.rafael.colaboradores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.colaboradores.model.Departamento;

public interface DepartamentoRepository  extends JpaRepository<Departamento, Long> {
   /*interface	Um contrato: diz o que existe, sem dizer como funciona
extends JpaRepository	Herda dezenas de métodos prontos
<Departamento, Long>	“Esta interface cuida da entidade Departamento, cuja chave primária é Long”
*/
}
