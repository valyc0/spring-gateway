package com.baeldung.springcloudgateway.customfilters.gatewayapp.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Ruolo {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "role")
    private Set<Utente> roleUtentes;

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

    public Set<Utente> getRoleUtentes() {
        return roleUtentes;
    }

    public void setRoleUtentes(final Set<Utente> roleUtentes) {
        this.roleUtentes = roleUtentes;
    }

}
