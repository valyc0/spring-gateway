package com.baeldung.springcloudgateway.customfilters.gatewayapp.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.baeldung.springcloudgateway.customfilters.gatewayapp.domain.Ruolo;


public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
}
