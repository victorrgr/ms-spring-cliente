package br.localizacao.msendereco.standard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponseError {
    private String timestamp;
    private String method;
    private String message;
    private String exception;
    private Integer status;
    private String path;
}