package com.guilherme.usuario.controller;

import com.guilherme.usuario.infrastructure.exceptions.ConflictException;
import com.guilherme.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.guilherme.usuario.infrastructure.exceptions.UnauthorizedException;
import com.guilherme.usuario.infrastructure.exceptions.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponseDTO> handlerResourceNotFoundExeption(ResourceNotFoundException exception,
                                                                     HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildError(HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI(),
                "Not Found"));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handlerConflictException(ConflictException exception,
                                                                     HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildError(HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                request.getRequestURI(),
                "Conflict"));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handlerUnauthorizedException(UnauthorizedException exception,
                                                                         HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(buildError(HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage(),
                request.getRequestURI(),
                "Unauthorized"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException exception,
                                                                           HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildError(HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage(),
                        request.getRequestURI(),
                        "Bad Request"
                ));
    }

    private ErrorResponseDTO buildError(int status, String mensage, String path, String error) {
        return ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .menssage(mensage)
                .error(error)
                .status(status)
                .path(path)
                .build();

    }
}

