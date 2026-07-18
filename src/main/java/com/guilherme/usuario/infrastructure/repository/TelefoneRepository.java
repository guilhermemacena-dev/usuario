package com.guilherme.usuario.infrastructure.repository;

import com.guilherme.aprendendospring.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Indica que esta interface é um repositório do Spring Data JPA.
// Ela será responsável pelas operações de acesso ao banco de dados
// relacionadas à entidade Telefone.
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    // Ao estender JpaRepository, esta interface herda automaticamente
    // diversos métodos prontos para manipulação da entidade Telefone,
    // como por exemplo:
    //
    // - save()        -> Salvar ou atualizar um telefone.
    // - findById()    -> Buscar um telefone pelo ID.
    // - findAll()     -> Listar todos os telefones.
    // - deleteById()  -> Excluir um telefone pelo ID.
    // - existsById()  -> Verificar se um telefone existe.
    //
    // Como nenhum método personalizado foi declarado,
    // apenas os métodos fornecidos pelo JpaRepository estarão disponíveis.

}
