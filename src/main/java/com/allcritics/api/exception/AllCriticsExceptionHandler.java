package com.allcritics.api.exception;

import com.allcritics.api.dto.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AllCriticsExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ApiError apiError = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(
            Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiError apiError = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                "Ocorreu um erro inesperado no servidor. Tente novamente mais tarde.",
                request.getDescription(false).replace("uri=", "")
        );

        // Opcional: Logar o erro real no console do servidor para depuração
        // ex.printStackTrace();

        return new ResponseEntity<>(apiError, status);
    }
}
