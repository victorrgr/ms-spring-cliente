package br.localizacao.msendereco.dto;

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
}