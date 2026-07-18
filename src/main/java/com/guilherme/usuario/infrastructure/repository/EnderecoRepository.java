package com.guilherme.usuario.infrastructure.repository;

import com.guilherme.aprendendospring.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que esta interface é um repositório do Spring Data JPA.
// Ela será responsável pelas operações de acesso ao banco de dados
// relacionadas à entidade Endereco.
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    // Ao estender JpaRepository, esta interface herda automaticamente
    // diversos métodos prontos para manipulação da entidade Endereco,
    // como por exemplo:
    //
    // - save()        -> Salvar ou atualizar um endereço.
    // - findById()    -> Buscar um endereço pelo ID.
    // - findAll()     -> Listar todos os endereços.
    // - deleteById()  -> Excluir um endereço pelo ID.
    // - existsById()  -> Verificar se um endereço existe.
    //
    // Como nenhum metodo personalizado foi declarado,
    // apenas os métodos fornecidos pelo JpaRepository estarão disponíveis.

}
