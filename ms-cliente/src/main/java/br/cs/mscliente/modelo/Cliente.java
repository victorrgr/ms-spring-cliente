package br.cs.mscliente.modelo;


import br.cs.mscliente.dto.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long enderecoId;
    private String complemento;
    private Integer numero;

    public static Cliente translate(ClienteDTO dto) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(dto, Cliente.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return id != null && Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
