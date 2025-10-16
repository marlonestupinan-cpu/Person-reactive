package com.example.Person.infrastructure.endpoints;

import com.example.Person.domain.model.Person;
import com.example.Person.infrastructure.endpoints.dto.RegisterPerson;
import com.example.Person.infrastructure.endpoints.handler.PersonHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/person",
                    method = RequestMethod.POST,
                    beanClass = PersonHandler.class,
                    beanMethod = "register",
                    operation = @Operation(
                            operationId = "registerPerson",
                            summary = "Register a new person",
                            tags = {"People"},
                            requestBody = @RequestBody(
                                    description = "New person data",
                                    required = true,
                                    content = @Content(schema = @Schema(implementation = RegisterPerson.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Person registered successfully"),
                                    @ApiResponse(responseCode = "400", description = "Invalid input data")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/person/best-bootcamp",
                    method = RequestMethod.GET,
                    beanClass = PersonHandler.class,
                    beanMethod = "getBestBootcamp",
                    operation = @Operation(
                            operationId = "getBestBootcamp",
                            summary = "Get the ID of the best bootcamp",
                            tags = {"People"},
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Successful operation",
                                            content = @Content(schema = @Schema(type = "integer", format = "int64"))),
                                    @ApiResponse(responseCode = "404", description = "No bootcamp found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/person/bootcamp/{id}",
                    method = RequestMethod.GET,
                    beanClass = PersonHandler.class,
                    beanMethod = "getPeopleFromBootcamp",
                    operation = @Operation(
                            operationId = "getPeopleFromBootcamp",
                            summary = "Get all people registered in a bootcamp",
                            tags = {"People"},
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Bootcamp ID", required = true)
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Successful operation",
                                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    array = @ArraySchema(schema = @Schema(implementation = Person.class)))),
                                    @ApiResponse(responseCode = "404", description = "Bootcamp not found")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(PersonHandler personHandler) {
        return route(POST("/person"), personHandler::register)
                .andRoute(GET("/person/best-bootcamp"), personHandler::getBestBootcamp)
                .andRoute(GET("/person/bootcamp/{id}"), personHandler::getPeopleFromBootcamp);
    }
}
