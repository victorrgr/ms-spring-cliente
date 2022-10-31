package br.localizacao.msendereco.service;

import br.localizacao.msendereco.dto.ClienteDTO;
import br.localizacao.msendereco.enums.ClienteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ClienteDTO> findAllClientesByEndereco(Long enderecoId) {
        String url = String.format(ClienteOperation.FIND_ALL_BY_ENDERECO.getUrl(), enderecoId);
        HttpMethod method = ClienteOperation.FIND_ALL_BY_ENDERECO.getMethod();
        ResponseEntity<List<ClienteDTO>> response;
        try {
            var type = new ParameterizedTypeReference<List<ClienteDTO>>() {};
            response = restTemplate.exchange(url, method, null, type);
        } catch (HttpStatusCodeException rce) {
            return null;
        }
        return response.getBody();
    }

}
