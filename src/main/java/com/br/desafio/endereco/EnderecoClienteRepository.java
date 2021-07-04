package com.br.desafio.endereco;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

public interface EnderecoClienteRepository extends MongoRepository<EnderecoClienteEntity, String> {

    EnderecoClienteDto findByCep(String cep);

}
