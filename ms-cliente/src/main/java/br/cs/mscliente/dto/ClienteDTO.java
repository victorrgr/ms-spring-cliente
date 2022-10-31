package br.cs.mscliente.dto;

import br.cs.mscliente.modelo.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private Long enderecoId;
    private String complemento;
    private Integer numero;

    public static ClienteDTO translate(Cliente entity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(entity, ClienteDTO.class);
    }

}
