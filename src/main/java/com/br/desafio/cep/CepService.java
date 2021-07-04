package com.br.desafio.cep;

import com.br.desafio.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CepService {

    private final RestTemplate restTemplate;

    public ResponseEntity<CepResponse> getEnderecoBy(String cep) {
        try {
            cep = cep.replace("-", "");

            final int tamanhoCep = 8;

            if (cep.isBlank() || cep.length() != 8)
                throw new RestException("CEP inválido.", HttpStatus.BAD_REQUEST);

            int posicao = cep.length() - 1;

            StringBuilder builder = new StringBuilder();
            builder.append(cep);

            while (posicao >= 0 && restTemplate.getForObject(this.montarPath(builder.toString()),
                    CepRequest.class).getCep() == null) {
                builder.setCharAt(posicao, '0');
                posicao--;
            }

            if (posicao == -1) throw new RestException("CEP não encontrado.", HttpStatus.BAD_REQUEST);

            return restTemplate.getForEntity(this.montarPath(builder.toString()), CepResponse.class);

        } catch (RestException e) {
            throw new RestException(e.getMessage(), e.getHttpStatus());

        } catch (Exception e) {
            if (e.getMessage().contains("400")) throw new RestException("Erro de requisição: CEP inválido.", HttpStatus.BAD_REQUEST);

            throw new RestException("Erro ao consultar serviço de CEP.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String montarPath(String sufixo) {
        return String.format("https://viacep.com.br/ws/%s/json/", sufixo);
    }
}
