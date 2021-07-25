package br.com.desafio.cep;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiModel(description = "Detalhes sobre o endereço do CEP")
@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor
public class CepResponse {

    @ApiModelProperty(notes = "Número do CEP", example = "12345-678")
    private String cep;
    @ApiModelProperty(notes = "Logradouro", example = "Rua dos Aventureiros")
    private String logradouro;
    @ApiModelProperty(notes = "Bairro", example = "Olímpico")
    private String bairro;
    @ApiModelProperty(notes = "Cidade", example = "São Paulo")
    private String localidade;
    @ApiModelProperty(notes = "Estado", example = "SP")
    private String uf;
}
