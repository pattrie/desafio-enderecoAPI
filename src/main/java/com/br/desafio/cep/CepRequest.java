package com.br.desafio.cep;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "Detalhes sobre o CEP")
@Getter
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor
public class CepRequest {

    @ApiModelProperty(notes = "Número do CEP", example = "12345-678")
    @NotBlank(message = "{value.not.blank}")
    @Max(value = 9, message = "{value.max}")
    @Min(value = 8, message = "{value.min}")
    private String cep;

}
