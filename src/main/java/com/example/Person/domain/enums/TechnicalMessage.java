package com.example.Person.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.helpers.MessageFormatter;

@Getter
@RequiredArgsConstructor
public enum TechnicalMessage {
    PERSON_ADDED("201", "Persona registrada correctamente"),
    INTERNAL_ERROR("500", "Algo salió mal, intentalo de nuevo"),
    INVALID_REQUEST("400", "Petición erronea, verifica los datos"),
    INVALID_PARAMETERS(INVALID_REQUEST.getCode(), "Parametros erroneos, por favor verifica los datos"),
    MIN_BOOTCAMP_NOT_REACHED("400", "La persona debe tener como mínimo {} bootcamp"),
    MAX_BOOTCAMP_NOT_REACHED("400", "La persona debe máximo {} bootcamp"),
    DUPLICATED_BOOTCAMP("400", "La persona no puede tener bootcamp repetidos"),

    BOOTCAMP_INTERSECTION("400", "El bootcamp {} coincide en el tiempo con el bootcamp {}");
    private final String code;
    private final String message;


    public String getMessage(String[] params) {
        return MessageFormatter.arrayFormat(message, params).getMessage();
    }
}
