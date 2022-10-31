package br.localizacao.msendereco.enums;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum ClienteOperation {
    FIND_ALL("http://cliente/cliente", HttpMethod.GET),
    FIND_ALL_BY_ENDERECO("http://cliente/cliente/endereco/%s", HttpMethod.GET),
    FIND_ID("http://cliente/cliente/%s", HttpMethod.GET),
    SAVE("http://cliente/cliente", HttpMethod.POST),
    UPDATE("http://cliente/cliente/%s", HttpMethod.PUT),
    DELETE("http://cliente/endereco/%s", HttpMethod.DELETE);

    private final String url;
    private final HttpMethod method;

    ClienteOperation(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }
}
