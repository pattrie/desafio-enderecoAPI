package com.br.desafio.exception;

public interface Validator<T> {
    void valida(T object) throws ValidatorException;
}
