package com.br.desafio.endereco;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnderecoClienteRepository extends MongoRepository<EnderecoClienteEntity, String> {

    EnderecoClienteDto findByCep(String cep);
}
