package br.com.desafio.controller;

import br.com.desafio.cep.CepController;
import br.com.desafio.cep.CepRequest;
import br.com.desafio.cep.CepResponse;
import br.com.desafio.cep.CepService;
import br.com.desafio.mother.CepMother;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({
        CepController.class,
        CepService.class
})
public class CepControllerUnitTest {

    private static final String ENDERECO_ENDPOINT = "/api/cep/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CepService cepService;

    @Test
    public void givenAValidCepNumberWhenGetEnderecoByThenReturnEndereco() throws Exception {
        CepRequest cepRequest = CepMother.getCep();
        CepResponse cepResponse = CepMother.getEnderecoByCep();

        given(cepService.getEnderecoBy("01001-000"))
                .willReturn(new ResponseEntity<>(cepResponse, HttpStatus.OK));

        Gson gson = new Gson();
        String jsonBody = String.valueOf(gson.toJson(cepRequest));

        mockMvc.perform(get(ENDERECO_ENDPOINT + cepRequest.getCep())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.cep").value(cepResponse.getCep()))
                .andExpect(jsonPath("$.logradouro").value(cepResponse.getLogradouro()))
                .andExpect(jsonPath("$.bairro").value(cepResponse.getBairro()))
                .andExpect(jsonPath("$.localidade").value(cepResponse.getLocalidade()))
                .andExpect(jsonPath("$.uf").value(cepResponse.getUf()));
    }

    @Test
    public void givenANonExistentCepNumberWhenGetEnderecoByThenReturnBadRequest() throws Exception {
        CepRequest cepRequest = CepMother.getCep();
        cepRequest.setCep("00000-000");

        given(cepService.getEnderecoBy("00000-000"))
                .willReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        Gson gson = new Gson();
        String jsonBody = String.valueOf(gson.toJson(cepRequest));

        mockMvc.perform(get(ENDERECO_ENDPOINT + cepRequest.getCep())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAMutableCepNumberWhenGetEnderecoByThenReturnAnotherCep() throws Exception {
        CepRequest cepRequest = CepMother.getCep();
        cepRequest.setCep("12345-678");
        CepResponse anotherCep = CepMother.getEnderecoByAnotherCep();

        given(cepService.getEnderecoBy("12345-678")).willReturn(
                new ResponseEntity<>(anotherCep, HttpStatus.OK));

        Gson gson = new Gson();
        String jsonBody = String.valueOf(gson.toJson(cepRequest));

        mockMvc.perform(get(ENDERECO_ENDPOINT + cepRequest.getCep())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                 .andExpect(jsonPath("$.cep").value(anotherCep.getCep()))
                .andExpect(jsonPath("$.logradouro").value(anotherCep.getLogradouro()))
                .andExpect(jsonPath("$.bairro").value(anotherCep.getBairro()))
                .andExpect(jsonPath("$.localidade").value(anotherCep.getLocalidade()))
                .andExpect(jsonPath("$.uf").value(anotherCep.getUf()));
    }

    @Test
    public void givenAEmptyCepNumberWhenGetEnderecoByThenReturnBadRequest() throws Exception {
        CepRequest cepRequest = CepMother.getCep();
        cepRequest.setCep(" ");

        given(cepService.getEnderecoBy(" ")).willReturn(
                new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        Gson gson = new Gson();
        String jsonBody = String.valueOf(gson.toJson(cepRequest));

        mockMvc.perform(get(ENDERECO_ENDPOINT + cepRequest.getCep())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenACepSizeDifferentEightWhenGetEnderecoByThenReturnBadRequest() throws Exception {
        CepRequest cepRequest = CepMother.getCep();
        cepRequest.setCep("99999-9999");

        given(cepService.getEnderecoBy("99999-9999"))
                .willReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        Gson gson = new Gson();
        String jsonBody = String.valueOf(gson.toJson(cepRequest));

        mockMvc.perform(get(ENDERECO_ENDPOINT + cepRequest.getCep())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
