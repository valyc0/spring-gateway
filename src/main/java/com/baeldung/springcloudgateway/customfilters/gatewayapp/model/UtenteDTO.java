package com.baeldung.springcloudgateway.customfilters.gatewayapp.model;

import javax.validation.constraints.Size;


public class UtenteDTO {

    private Long id;

    @Size(max = 255)
    private String nome;

    @Size(max = 255)
    private String cognome;

    @Size(max = 255)
    private String username;

    private Long role;
    
    private String roleName;

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(final String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(final Long role) {
        this.role = role;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UtenteDTO [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cognome=");
		builder.append(cognome);
		builder.append(", username=");
		builder.append(username);
		builder.append(", role=");
		builder.append(role);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append("]");
		return builder.toString();
	}

}
