package br.com.desafio.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnderecoClienteService {

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Autowired
    private EnderecoClienteMapper enderecoClienteMapper;

    public Object addEndereco(EnderecoClienteDto endereco) {
        try {
            return enderecoClienteMapper.toEnderecoDto(
                    enderecoClienteRepository.save(enderecoClienteMapper.toEndereco(endereco)));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity getEnderecoBy(String cep) {
        try {
            EnderecoClienteDto endereco = enderecoClienteRepository.findByCep(cep);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
