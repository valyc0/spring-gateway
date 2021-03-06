package com.baeldung.springcloudgateway.customfilters.gatewayapp.filters.factories;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractChangeRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RequestHeaderToRequestUriGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
@Order(2)
public class ChangeDestinationUriGatewayFilterFactory extends
        AbstractChangeRequestUriGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
    
	
	private final Logger log = LoggerFactory
            .getLogger(RequestHeaderToRequestUriGatewayFilterFactory.class);

    public ChangeDestinationUriGatewayFilterFactory() {
        super(NameConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    protected Optional<URI> determineRequestUri(ServerWebExchange exchange,
            NameConfig config) {
        
        String requestUrl = determineUrl(exchange);
        return Optional.ofNullable(requestUrl).map(url -> {
            try {
                return new URL(url).toURI();
            }
            catch (MalformedURLException | URISyntaxException e) {
                log.info("Request url is invalid : url={}, error={}", requestUrl,
                        e.getMessage());
                return null;
            }
        });
    }

	private String determineUrl(ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		String url = exchange.getAttribute("URL_TO_SEND");
		return url+"/EstrattoContoAppImpl/EstrattoContoService";
	}

	
}