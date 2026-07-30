package com.guilherme.usuario.infrastructure.exceptions;

// Exceção personalizada utilizada para indicar que um recurso
// solicitado não foi encontrado na aplicação.
// Exemplo: buscar um usuário por e-mail que não existe no banco de dados.
public class ResourceNotFoundException extends RuntimeException{

    // Construtor que recebe apenas a mensagem da exceção.
    // A mensagem é repassada para a classe RuntimeException.
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }

    // Construtor que recebe uma mensagem e a exceção que originou o erro.
    // O Throwable é passado para a superclasse, preservando a causa
    // original da exceção para facilitar a depuração.
    public ResourceNotFoundException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}