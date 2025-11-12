package com.example.petconnect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profiles") // Define o nome da coleção no MongoDB
public class Profile {

    @Id
    private String id;

    private String nomeCompleto;
    private String numeroTelefone;
    private String endereco;

   
    public Profile() {}

 
    public Profile(String nomeCompleto, String numeroTelefone, String endereco) {
        this.nomeCompleto = nomeCompleto;
        this.numeroTelefone = numeroTelefone;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
