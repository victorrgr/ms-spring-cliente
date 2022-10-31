package br.cs.mscliente.repository;

import br.cs.mscliente.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByEnderecoId(Long enderecoId);
}
