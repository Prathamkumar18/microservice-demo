package com.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("demo", r -> r.path("/get").filters(f-> f.addRequestHeader("MyHeader", "MyUri")).uri("http://httpbin.org"))
				.route("currency_exchange", r -> r.path("/currency-exchange/**").uri("lb://CURRENCY-EXCHANGE-SERVICE"))
				.route("currency_conversion", r -> r.path("/currency-conversion-feign/**").uri("lb://CURRENCY-CONVERSION-SERVICE"))
				.build();
	}
}
