package br.cs.mscliente.enums;

import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
public enum EnderecoOperation {
    FIND_ALL("http://endereco/endereco", HttpMethod.GET),
    FIND_ID("http://endereco/endereco/%s", HttpMethod.GET),
    SAVE("http://endereco/endereco", HttpMethod.POST),
    UPDATE("http://endereco/endereco/%s", HttpMethod.PUT),
    DELETE("http://endereco/endereco/%s", HttpMethod.DELETE);

    private final String url;
    private final HttpMethod method;

    EnderecoOperation(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }
}
