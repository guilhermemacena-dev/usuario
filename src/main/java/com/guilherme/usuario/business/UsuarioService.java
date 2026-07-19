package com.guilherme.usuario.business;

import com.guilherme.usuario.business.converter.UsuarioConverter;
import com.guilherme.usuario.business.dto.UsuarioDTO;
import com.guilherme.usuario.infrastructure.entity.Usuario;
import com.guilherme.usuario.infrastructure.exceptions.ConflictException;
import com.guilherme.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.guilherme.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){

        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }

    public void emailExiste(String email){
        try {

            boolean existe = verificaEmailExistente(email);

            if(existe){
                throw new ClassCastException("Email já cadastrado" + email);
            }

        } catch (ConflictException e){

            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){

        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String emial){

        return usuarioRepository.findByEmail(emial).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado: " + emial));
    }

    // Remove um usuário do banco de dados utilizando o e-mail como referência.
    public void deletaUsuarioPorEmail(String email){

        usuarioRepository.deleteByEmail(email);
    }
}
