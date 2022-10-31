package br.localizacao.msendereco.modelo;

import br.localizacao.msendereco.dto.ClienteEnderecoDTO;
import br.localizacao.msendereco.dto.EnderecoDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private Integer cep;
    private String bairro;
    private String cidade;
    @Enumerated(EnumType.STRING)
    private UnidadeFederativa uf;

    public static Endereco translate(EnderecoDTO entity) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(entity, Endereco.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Endereco endereco = (Endereco) o;
        return id != null && Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
