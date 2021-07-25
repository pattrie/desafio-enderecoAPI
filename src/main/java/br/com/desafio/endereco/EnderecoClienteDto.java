package br.com.desafio.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoClienteDto {

    private String id;
    @NotBlank(message = "{value.not.blank}")
    private String cep;
    @NotBlank(message = "{value.not.blank}")
    private String logradouro;
    @NotBlank(message = "{value.not.blank}")
    private String bairro;
    @NotBlank(message = "{value.not.blank}")
    private String localidade;
    @NotBlank(message = "{value.not.blank}")
    private String uf;
    @NotBlank(message = "{value.not.blank}")
    private String numero;
    private String complemento;
}
