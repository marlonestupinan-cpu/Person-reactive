package com.example.Person.infrastructure.endpoints.handler;

import com.example.Person.domain.api.IPersonServicePort;
import com.example.Person.domain.enums.TechnicalMessage;
import com.example.Person.domain.exceptions.BusinessException;
import com.example.Person.infrastructure.endpoints.dto.RegisterPerson;
import com.example.Person.infrastructure.endpoints.mappers.IPersonDtoMapper;
import com.example.Person.infrastructure.endpoints.util.APIResponse;
import com.example.Person.infrastructure.endpoints.util.ErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;

import static com.example.Person.domain.enums.TechnicalMessage.PERSON_ADDED;

@Component
@RequiredArgsConstructor
@Log4j2
public class PersonHandler {
    private final IPersonServicePort personServicePort;
    private final IPersonDtoMapper personDtoMapper;

    public Mono<ServerResponse> register(ServerRequest request) {
        return request
                .bodyToMono(RegisterPerson.class)
                .map(personDtoMapper::toPerson)
                .flatMap(personServicePort::registerPerson)
                .flatMap(bootcamp ->
                        ServerResponse
                                .ok()
                                .bodyValue(PERSON_ADDED.getMessage()))
                .transform(errorHandler());
    }

    public Mono<ServerResponse> getBestBootcamp(ServerRequest request) {
        return personServicePort
                .getBestBootcamp()
                .flatMap(idBootcamp -> ServerResponse.ok().bodyValue(idBootcamp))
                .transform(errorHandler());
    }

    public Mono<ServerResponse> getPeopleFromBootcamp(ServerRequest request) {
        Long id = Long.parseLong(request.pathVariable("id"));
        return personServicePort
                .getPeopleFromBootcamp(id)
                .collectList()
                .flatMap(people ->
                        ServerResponse.ok().bodyValue(people))
                .transform(errorHandler());
    }

    private Mono<ServerResponse> buildErrorResponse(HttpStatus httpStatus, TechnicalMessage error,
                                                    List<ErrorDTO> errors) {
        return Mono.defer(() -> {
            APIResponse apiErrorResponse = APIResponse
                    .builder()
                    .code(error.getCode())
                    .message(error.getMessage())
                    .date(Instant.now().toString())
                    .errors(errors)
                    .build();
            return ServerResponse.status(httpStatus)
                    .bodyValue(apiErrorResponse);
        });
    }

    private <T> Function<Mono<ServerResponse>, Mono<ServerResponse>> errorHandler() {
        return monoStream ->
                monoStream
                        .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
                                HttpStatus.BAD_REQUEST,
                                TechnicalMessage.INVALID_PARAMETERS,
                                List.of(ErrorDTO.builder()
                                        .code(ex.getTechnicalMessage().getCode())
                                        .message(ex.getMessage())
                                        .build())))
                        .onErrorResume(ex -> {
                                    log.error("Unexpected error occurred for messageId: ", ex);
                                    return buildErrorResponse(
                                            HttpStatus.INTERNAL_SERVER_ERROR,
                                            TechnicalMessage.INTERNAL_ERROR,
                                            List.of(ErrorDTO.builder()
                                                    .code(TechnicalMessage.INTERNAL_ERROR.getCode())
                                                    .message(TechnicalMessage.INTERNAL_ERROR.getMessage())
                                                    .build()));
                                }
                        );
    }
}
