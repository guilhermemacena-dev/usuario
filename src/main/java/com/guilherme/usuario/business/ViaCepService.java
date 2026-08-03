package com.guilherme.usuario.business;

import com.guilherme.usuario.infrastructure.clients.ViaCepClient;
import com.guilherme.usuario.infrastructure.clients.ViaCepDTO;
import com.guilherme.usuario.infrastructure.exceptions.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep){
        return client.buscaDadosEndereco(processarCep(cep));

    }

    private String processarCep(String cep){
        //replace() faz uma substituição literal, diferente do replaceAll() que usa expressões regulares (regex)
        String cepFormatado = cep.replace(" ", "").replace("-", "");

        if(!cepFormatado.matches("\\d+") || !Objects.equals(cepFormatado.length(), 8)){
            throw  new IllegalArgumentException("O CEP contém caracteres inválidos. Por favor, verifique.");
            }

            return cepFormatado;
        }


    }
