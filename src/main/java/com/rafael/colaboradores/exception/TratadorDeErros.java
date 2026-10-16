package com.rafael.colaboradores.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice //"vigia" os erros de TODOS os controllers
public class TratadorDeErros {

     // quando acontecer RecursoNaoEncontradoException -> responde 404

     @ExceptionHandler (RecursoNaoEncontradoException.class)
        public ResponseEntity<Map<String, String>> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
            Map<String, String> corpo = new HashMap<>();
            corpo.put("erro", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpo);
        }
    
        // quando a validacao falhar (ex: nome vazio) -> responde 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> erros.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(erros);
    }
}
