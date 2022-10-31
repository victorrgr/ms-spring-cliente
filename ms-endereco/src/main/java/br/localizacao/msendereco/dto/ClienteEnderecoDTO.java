package br.localizacao.msendereco.dto;

import br.localizacao.msendereco.modelo.Endereco;
import br.localizacao.msendereco.modelo.UnidadeFederativa;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEnderecoDTO implements EnderecoDTO {
    private Long id;
    private String logradouro;
    private Integer cep;
    private String bairro;
    private String cidade;
    private UnidadeFederativa uf;
    private List<ClienteDTO> clientes;
    public static ClienteEnderecoDTO translate(Endereco entity) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(entity, ClienteEnderecoDTO.class);
    }
}
