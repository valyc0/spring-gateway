package com.baeldung.springcloudgateway.customfilters.gatewayapp.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.baeldung.springcloudgateway.customfilters.gatewayapp.domain.Utente;


public interface UtenteRepository extends JpaRepository<Utente, Long> {
	
	Utente findByUsername(String username);
}
