package com.example.petconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ongs")
public class Ong {

    @Id
    private String id;

    private String nomeOng;
    private String telefone;
    private String endereco;
    private String redeSocial;
    private String responsavel;
    private String email;
    private String descricao;

 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNomeOng() {
        return nomeOng;
    }
    public void setNomeOng(String nomeOng) {
        this.nomeOng = nomeOng;
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

    public String getRedeSocial() {
        return redeSocial;
    }
    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }

    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
