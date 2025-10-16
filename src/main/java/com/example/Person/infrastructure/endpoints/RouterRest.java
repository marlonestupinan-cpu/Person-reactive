package com.example.Person.infrastructure.endpoints;

import com.example.Person.infrastructure.endpoints.handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(PersonHandler personHandler) {
        return route(POST("/person"), personHandler::register)
                .andRoute(GET("/person/best-bootcamp"), personHandler::getBestBootcamp)
                .andRoute(GET("/person/bootcamp/{id}"), personHandler::getPeopleFromBootcamp);
    }
}
