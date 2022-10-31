package br.localizacao.msendereco.dto;

import br.localizacao.msendereco.modelo.Endereco;
import br.localizacao.msendereco.modelo.UnidadeFederativa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEnderecoDTO implements EnderecoDTO {
    private Long id;
    private String logradouro;
    private Integer cep;
    private String bairro;
    private String cidade;
    private UnidadeFederativa uf;
    public static ResponseEnderecoDTO translate(Endereco entity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(entity, ResponseEnderecoDTO.class);
    }
}
