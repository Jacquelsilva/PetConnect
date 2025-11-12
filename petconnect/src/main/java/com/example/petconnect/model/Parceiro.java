package com.example.petconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parceiros")
public class Parceiro {

    @Id
    private String id;

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;
    private String descricao; // Adicionado para combinar com o service

    public Parceiro() {
    }

    public Parceiro(String nome, String email, String telefone, String endereco, String descricao) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getSenha() {
    return senha;
}

public void setSenha(String senha) {
    this.senha = senha;
}

}
