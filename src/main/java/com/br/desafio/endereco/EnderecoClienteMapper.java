package com.br.desafio.endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoClienteMapper {

    EnderecoClienteDto toEnderecoDto(EnderecoClienteEntity endereco);

    EnderecoClienteEntity toEndereco(EnderecoClienteDto endereco);
}
