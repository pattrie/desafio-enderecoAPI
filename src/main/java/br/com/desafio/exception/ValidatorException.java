package br.com.desafio.exception;

public class ValidatorException extends Exception {
    public ValidatorException(Exception e){
        super(e);
    }
}
