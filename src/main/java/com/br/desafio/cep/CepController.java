package com.br.desafio.cep;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/api/cep", tags = "CEP Controller")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o endereço"),
        @ApiResponse(code = 400, message = "CEP inválido / CEP não encontrado / Erro de requisição"),
        @ApiResponse(code = 500, message = "Erro ao consultar serviço de CEP"),
})
@RestController
@RequestMapping(value = "/api/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService){
        this.cepService = cepService;
    }

    @ApiOperation(value = "Retorna um endereço dado um determinado número de CEP")
    @ApiImplicitParams(@ApiImplicitParam(name = "cep", value = "Adicione um CEP válido.", required = true))
    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> getAddressBy(@PathVariable("cep") CepRequest cep) {
        return cepService.getEnderecoBy(cep.getCep());
    }
}
