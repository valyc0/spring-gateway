package com.baeldung.springcloudgateway.customfilters.gatewayapp.filters.factories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import com.baeldung.springcloudgateway.customfilters.gatewayapp.model.UtenteDTO;
import com.baeldung.springcloudgateway.customfilters.gatewayapp.service.UtenteService;

@Component
public class ModifyRequestHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<ModifyRequestHeaderGatewayFilterFactory.Config> {

	@Autowired
	UtenteService utenteService;
	
    final Logger logger = LoggerFactory.getLogger(ModifyRequestHeaderGatewayFilterFactory.class);
	private List<String> list;

    public ModifyRequestHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("defaultLocale");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
        	System.out.println();
        	
        	HttpHeaders headers = exchange.getRequest().getHeaders();
        	String val = headers.getFirst("x-proxy-val");
        	UtenteDTO byUsername = utenteService.getByUsername(val);
        	
        	if(byUsername!=null) {
        		System.out.println(byUsername.toString());
        		System.out.println("trovato vale!!!");
        		 ServerHttpRequest newHttp = exchange.getRequest()
        	             .mutate()
        	             .header("x-proxy-user", byUsername.getNome())
        	             .header("x-proxy-roles", byUsername.getRoleName())
        	             .build();
        	            
        	        	 ServerWebExchange modifiedExchange = exchange.mutate().request(newHttp).build();

        	            return chain.filter(modifiedExchange);
        		
        		
        	}
        	else {
        		System.out.println("proseguo normale");
        		return chain.filter(exchange);
        	}

            
        };
    }

    public static class Config {
        private Locale defaultLocale;

        public Config() {
        }

        public Locale getDefaultLocale() {
            return defaultLocale;
        }

        public void setDefaultLocale(String defaultLocale) {
            this.defaultLocale = Locale.forLanguageTag(defaultLocale);
        };
    }
}
