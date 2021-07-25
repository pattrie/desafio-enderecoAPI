package br.com.desafio.cep;

import br.com.desafio.exception.RestException;
import br.com.desafio.exception.Validator;
import org.springframework.http.HttpStatus;

public class CepValidator implements Validator<String> {

    @Override
    public void valida(String cep) {
        final int tamanhoCep = 8;
        if (cep.isBlank() || cep.length() != 8)
            throw new RestException("CEP inválido.", HttpStatus.BAD_REQUEST);
    }
}
