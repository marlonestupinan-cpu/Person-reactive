package com.example.Person.domain.exceptions;

import com.example.Person.domain.enums.TechnicalMessage;
import lombok.Getter;

@Getter
public class BusinessException extends ProcessorException {
    public BusinessException(TechnicalMessage technicalMessage, String... params) {
        super(technicalMessage.getMessage(params), technicalMessage);
    }
}