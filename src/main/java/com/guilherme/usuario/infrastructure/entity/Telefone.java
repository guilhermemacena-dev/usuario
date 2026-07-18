package com.guilherme.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Gera automaticamente os métodos getters para todos os atributos da classe.
@Setter // Gera automaticamente os métodos setters para todos os atributos da classe.
@AllArgsConstructor // Gera um construtor contendo todos os atributos da classe.
@NoArgsConstructor // Gera um construtor sem parâmetros.

// Indica que esta classe representa uma entidade do banco de dados.
@Entity

// Define o nome da tabela que será criada ou utilizada no banco de dados.
@Table(name = "telefone")
public class Telefone {

    // Chave primária da entidade.
    @Id

    // Define que o valor do ID será gerado automaticamente pelo banco de dados
    // utilizando a estratégia de auto incremento (IDENTITY).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapeia o atributo "numero" para a coluna "numero".
    // O tamanho máximo permitido é de 10 caracteres.
    @Column(name = "numero", length = 10)
    private String numero;

    // Mapeia o atributo "ddd" para a coluna "ddd".
    // O tamanho máximo permitido é de 3 caracteres
    // (ex.: 011, 083, 021).
    @Column(name = "ddd", length = 3)
    private String ddd;

}
