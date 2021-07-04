package com.br.desafio.mother;

import com.br.desafio.cep.CepRequest;
import com.br.desafio.cep.CepResponse;

public class CepMother {
    public static CepResponse getEnderecoByCep() {
        return new CepResponse("01001-000", "Praça da Sé", "Sé",
                "São Paulo", "SP");
    }

    public static CepResponse getEnderecoByAnotherCep() {
        return new CepResponse("12345-678", "Avenida São Gabriel",
                "Jardim São Gabriel", "Jacareí", "SP");
    }

    public static CepRequest getCep(){
        return new CepRequest("01001-000");
    }
}
