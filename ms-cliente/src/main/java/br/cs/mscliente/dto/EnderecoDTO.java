package br.cs.mscliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String logradouro;
    private Integer cep;
    private String bairro;
    private String cidade;
    private UnidadeFederativa uf;
}

