package br.localizacao.msendereco.repository;

import br.localizacao.msendereco.modelo.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Endereco findByCidade(String cidade);
}
