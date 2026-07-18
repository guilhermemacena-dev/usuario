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
@Table(name = "endereco")
public class Endereco {

    // Chave primária da entidade.
    @Id

    // Define que o valor do ID será gerado automaticamente pelo banco de dados
    // utilizando a estratégia de auto incremento (IDENTITY).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapeia o atributo "rua" para a coluna "rua" da tabela.
    @Column(name = "rua")
    private String rua;

    // Mapeia o atributo "numero" para a coluna "numero".
    private Long numero;

    // Mapeia o atributo "complemento" para a coluna "complemento".
    // O tamanho máximo permitido é de 10 caracteres.
    @Column(name = "complemento", length = 10)
    private String complemento;

    // Mapeia o atributo "cidade" para a coluna "cidade".
    // O tamanho máximo permitido é de 150 caracteres.
    @Column(name = "cidade", length = 150)
    private String cidade;

    // Mapeia o atributo "estado" para a coluna "estado".
    // O tamanho máximo permitido é de 2 caracteres
    // (geralmente utilizado para a sigla do estado, como PB, SP, RJ).
    @Column(name = "estado", length = 2)
    private String estado;

    // Mapeia o atributo "cep" para a coluna "cep".
    // O tamanho máximo permitido é de 9 caracteres
    // (formato: 00000-000).
    @Column(name = "cep", length = 9)
    private String cep;

}