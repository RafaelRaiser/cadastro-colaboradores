package com.rafael.colaboradores.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rafael.colaboradores.exception.RecursoNaoEncontradoException;
import com.rafael.colaboradores.model.Colaborador;
import com.rafael.colaboradores.model.Departamento;
import com.rafael.colaboradores.repository.ColaboradorRepository;
import com.rafael.colaboradores.repository.DepartamentoRepository;

@Service//O spring vai gerenciar esta classe, ou seja, vai criar um objeto dela e injetar em outras classes
public class ColaboradorService {

private final ColaboradorRepository colaboradorRepository;
private final DepartamentoRepository departamentoRepository;

//INJECAO DE DEPENDENCIA: o spring entrega os repositorios para o service, sem que o service precise criar os repositorios
public ColaboradorService(ColaboradorRepository colaboradorRepository, DepartamentoRepository departamentoRepository) {
    this.colaboradorRepository = colaboradorRepository;
    this.departamentoRepository = departamentoRepository;
}

public List<Colaborador> listar(){
    return colaboradorRepository.findAll();
}

public Colaborador buscarPorId(Long id){    return colaboradorRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Colaborador não encontrado com id: " + id));
}

public List<Colaborador> buscarPorCargo(String cargo){
    return colaboradorRepository.findByCargoContainingIgnoreCase(cargo);
}

public Colaborador salvar(Colaborador colaborador){

    aplicarDepartamento(colaborador);
    return colaboradorRepository.save(colaborador);

}

    public Colaborador atualizar(Long id, Colaborador dados) {
        Colaborador existente = buscarPorId(id); // se nao existir, ja da erro aqui
        existente.setNome(dados.getNome());
        existente.setCargo(dados.getCargo());
        existente.setDataAdmissao(dados.getDataAdmissao());
        existente.setDepartamento(dados.getDepartamento());
        aplicarDepartamento(existente);
        return colaboradorRepository.save(existente);
    }
   public void excluir(long id){
    
       Colaborador existente = buscarPorId(id); // se nao existir, ja da erro aqui
       colaboradorRepository.delete(existente);
   }

    //REGRA DE NEGOCIO: o departamento informado precisa existir no banco
    private void aplicarDepartamento(Colaborador colaborador){

        Departamento informado = colaborador.getDepartamento();
        if (informado == null || informado.getId() == null){
            colaborador.setDepartamento(null);
            return;
        } 

        Departamento real = departamentoRepository.findById(informado.getId()).orElseThrow(() -> new RecursoNaoEncontradoException("Departamento nao encontrado com o id " + informado.getId()));
    }
}
