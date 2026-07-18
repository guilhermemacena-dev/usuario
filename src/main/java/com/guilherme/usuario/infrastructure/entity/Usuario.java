package com.guilherme.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter // Gera automaticamente os métodos getters para todos os atributos da classe.
@Setter // Gera automaticamente os métodos setters para todos os atributos da classe.
@AllArgsConstructor // Gera um construtor contendo todos os atributos da classe.
@NoArgsConstructor // Gera um construtor sem parâmetros.

// Indica que esta classe representa uma entidade do banco de dados.
@Entity

// Define que esta entidade será mapeada para a tabela "usuario".
@Table(name = "usuario")
public class Usuario implements UserDetails {

    // Chave primária da entidade.
    @Id

    // Define que o ID será gerado automaticamente pelo banco de dados
    // utilizando a estratégia de auto incremento (IDENTITY).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapeia o atributo "nome" para a coluna "nome".
    // O tamanho máximo permitido é de 100 caracteres.
    @Column(name = "nome", length = 100)
    private String nome;

    // Mapeia o atributo "email" para a coluna "email".
    // O tamanho máximo permitido é de 100 caracteres.
    @Column(name = "email", length = 100)
    private String email;

    // Mapeia o atributo "senha" para a coluna "senha".
    // A senha será armazenada já criptografada.
    @Column(name = "senha")
    private String senha;

    // Relacionamento de um para muitos (OneToMany).
    // Um usuário pode possuir vários endereços.
    //
    // CascadeType.ALL faz com que operações realizadas no usuário
    // (salvar, atualizar, excluir, etc.) sejam propagadas para os endereços.
    @OneToMany(cascade = CascadeType.ALL)

    // Cria a coluna "usuario_id" na tabela "endereco",
    // fazendo referência ao campo "id" da tabela "usuario".
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> enderecos;

    // Relacionamento de um para muitos (OneToMany).
    // Um usuário pode possuir vários telefones.
    //
    // CascadeType.ALL faz com que as operações realizadas no usuário
    // também sejam aplicadas aos telefones relacionados.
    @OneToMany(cascade = CascadeType.ALL)

    // Cria a coluna "usuario_id" na tabela "telefone",
    // relacionando cada telefone ao usuário correspondente.
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefones;

    // Método exigido pela interface UserDetails.
    // Retorna as permissões (roles) do usuário autenticado.
    // Neste caso, retorna uma lista vazia, indicando que o usuário
    // não possui autoridades definidas.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // Metodo utilizado pelo Spring Security para obter a senha
    // do usuário durante o processo de autenticação.
    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    // Metodo utilizado pelo Spring Security para obter
    // o identificador do usuário durante a autenticação.
    // Neste projeto, o e-mail é utilizado como username.
    @Override
    public String getUsername() {
        return email;
    }
}
