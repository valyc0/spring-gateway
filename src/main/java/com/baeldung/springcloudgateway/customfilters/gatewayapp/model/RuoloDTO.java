package com.baeldung.springcloudgateway.customfilters.gatewayapp.model;

import javax.validation.constraints.Size;


public class RuoloDTO {

    private Long id;

    @Size(max = 255)
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

}
