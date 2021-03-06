package br.com.desafio.mother;

import br.com.desafio.cep.CepRequest;
import br.com.desafio.cep.CepResponse;

public class CepMother {
    public static CepResponse getEnderecoByCep() {
        return new CepResponse("01001-000", "Praça da Sé", "Sé",
                "São Paulo", "SP");
    }

    public static CepResponse getEnderecoByAnotherCep() {
        return new CepResponse("1234-000", "Avenida São Gabriel",
                "Jardim São Gabriel", "Jacareí", "SP");
    }

    public static CepRequest getCep(){
        return new CepRequest("01001-000");
    }
}
