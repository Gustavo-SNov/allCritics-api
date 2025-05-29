package com.projeto_web.AllCritics.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<Map<String,Object>> handleValidacaoException(ValidacaoException ex, WebRequest request) {
        String traceMessage = "Arquivo: %s - Linha: %d - Método: %s";
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.valueOf(400).value());
        body.put("Erro", ex.getClass().getSimpleName());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        body.put("trace", Arrays.stream(ex.getStackTrace()).map(e -> String.format(traceMessage,e.getFileName(),e.getLineNumber(),e.getMethodName()))); // Você pode remover ou preencher conforme necessário
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
