package com.br.desafio.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteService enderecoClienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Object addEndereco(@RequestBody @Valid EnderecoClienteDto endereco){
        return enderecoClienteService.addEndereco(endereco);
    }

    @GetMapping
    public ResponseEntity getAddressBy(@RequestBody @Valid EnderecoClienteDto endereco) {
        return enderecoClienteService.getEnderecoBy(endereco.getCep());
    }
}
