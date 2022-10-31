package br.cs.mscliente.service;

import br.cs.mscliente.dto.EnderecoDTO;
import br.cs.mscliente.enums.EnderecoOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteService {

    @Autowired
    private RestTemplate restTemplate;

    public EnderecoDTO findEnderecoById(Long enderecoId) {
        String url = String.format(EnderecoOperation.FIND_ID.getUrl(), enderecoId);
        HttpMethod method = EnderecoOperation.FIND_ID.getMethod();
        ResponseEntity<EnderecoDTO> response;
        try {
            response = restTemplate.exchange(url, method, null, EnderecoDTO.class);
        } catch (HttpStatusCodeException rce) {
            return null;
        }
        return response.getBody();
    }

}
