package com.baeldung.springcloudgateway.customfilters.gatewayapp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.baeldung.springcloudgateway.customfilters.gatewayapp.domain")
@EnableJpaRepositories("com.baeldung.springcloudgateway.customfilters.gatewayapp.repos")
@EnableTransactionManagement
public class DomainConfig {
}
