package br.localizacao.msendereco.controller;

import br.localizacao.msendereco.dto.ClienteEnderecoDTO;
import br.localizacao.msendereco.dto.ResponseEnderecoDTO;
import br.localizacao.msendereco.modelo.Endereco;
import br.localizacao.msendereco.repository.EnderecoRepository;
import br.localizacao.msendereco.service.EnderecoService;
import br.localizacao.msendereco.standard.StandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController extends StandardController {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    private ResponseEntity<List<ResponseEnderecoDTO>> findAll() {
        var list = repository.findAll();
        if (list.isEmpty())
            return ResponseEntity.noContent().build();
        List<ResponseEnderecoDTO> listDto = new ArrayList<>();
        for (var entity : list)
            listDto.add(ResponseEnderecoDTO.translate(entity));
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/cidade/{cidade}")
    private ResponseEntity<ClienteEnderecoDTO> findByCidade(@PathVariable("cidade") String cidade) {
        var entity = repository.findByCidade(cidade);
        if (entity == null)
            return ResponseEntity.noContent().build();
        var clientes = enderecoService.findAllClientesByEndereco(entity.getId());
        var responseDto = ClienteEnderecoDTO.translate(entity);
        responseDto.setClientes(clientes);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseEnderecoDTO> findById(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        var responseDto = ResponseEnderecoDTO.translate(entity.get());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    private ResponseEntity<ResponseEnderecoDTO> save(@RequestBody @Validated ClienteEnderecoDTO dto) {
        var entity = Endereco.translate(dto);
        entity = repository.save(entity);
        var responseDto = ResponseEnderecoDTO.translate(entity);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ResponseEnderecoDTO> update(@PathVariable Long id, @RequestBody @Validated ClienteEnderecoDTO dto) {
        var entity = Endereco.translate(dto);
        var actualEntity = repository.findById(id);
        if (actualEntity.isEmpty())
            return ResponseEntity.notFound().build();
        entity.setId(id);
        entity = repository.save(entity);
        var responseDto = ResponseEnderecoDTO.translate(entity);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ClienteEnderecoDTO> delete(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        repository.delete(entity.get());
        return ResponseEntity.ok().build();
    }

}