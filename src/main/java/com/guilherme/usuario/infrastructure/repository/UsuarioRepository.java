package com.guilherme.usuario.infrastructure.repository;

import com.guilherme.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Indica que esta interface é um repositório do Spring Data JPA.
// Ela será responsável pelas operações de acesso ao banco de dados
// relacionadas à entidade Usuario.
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Verifica se existe um usuário com o e-mail informado.
    // O Spring Data JPA cria automaticamente a consulta
    // com base no nome do método.
    boolean existsByEmail(String email);

    // Busca um usuário pelo e-mail.
    // Retorna um Optional<Usuario>, permitindo tratar de forma segura
    // a possibilidade de o usuário não existir.
    Optional<Usuario> findByEmail(String email);

    // Remove um usuário pelo e-mail informado.
    // A anotação @Transactional garante que a operação de exclusão
    // seja executada dentro de uma transação com o banco de dados.
    @Transactional
    void deleteByEmail(String email);

}
