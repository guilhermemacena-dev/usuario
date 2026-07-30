package com.guilherme.usuario.infrastructure.exceptions;

// Exceção personalizada utilizada para representar situações de conflito
// na aplicação, como por exemplo um e-mail já cadastrado.
public class ConflictException extends RuntimeException{

    // Construtor que recebe apenas a mensagem da exceção.
    // A mensagem é repassada para a classe RuntimeException.
    public ConflictException(String mensagem){
        super(mensagem);
    }

    // Construtor que recebe uma mensagem e a exceção que originou o problema.
    // Geralmente é utilizado para preservar a causa original da exceção.
    public ConflictException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
