package br.com.desafio.exception;

public interface Validator<T> {
    void valida(T object) throws ValidatorException;
}
