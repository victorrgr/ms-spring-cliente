package br.cs.mscliente.controller;

import br.cs.mscliente.dto.ClienteDTO;
import br.cs.mscliente.modelo.Cliente;
import br.cs.mscliente.repository.ClienteRepository;
import br.cs.mscliente.service.ClienteService;
import br.cs.mscliente.standard.StandardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends StandardController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    private ResponseEntity<List<ClienteDTO>> findAll() {
        var list = repository.findAll();
        if (list.isEmpty())
            return ResponseEntity.noContent().build();
        List<ClienteDTO> listDto = new ArrayList<>();
        for (var entity : list)
            listDto.add(ClienteDTO.translate(entity));
        return ResponseEntity.ok(listDto);
    }

    // TODO: Possivelmente refazer este endpoint no modelo filter /cliente?enderecoId=X
    @GetMapping("/endereco/{enderecoId}")
    private ResponseEntity<List<ClienteDTO>> findAllByEndereco(@PathVariable("enderecoId") Long enderecoId) {
        var list = repository.findAllByEnderecoId(enderecoId);
        if (list.isEmpty())
            return ResponseEntity.noContent().build();
        List<ClienteDTO> listDto = new ArrayList<>();
        for (var entity : list)
            listDto.add(ClienteDTO.translate(entity));
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ClienteDTO> findById(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        var responseDto = ClienteDTO.translate(entity.get());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    private ResponseEntity<ClienteDTO> save(@RequestBody @Validated ClienteDTO requestDto) {
        var entity = Cliente.translate(requestDto);
        var endereco = clienteService.findEnderecoById(requestDto.getEnderecoId());
        if (endereco == null)
            throw new IllegalArgumentException("Endereço informado não existe");

        entity.setEnderecoId(endereco.getId());
        entity = repository.save(entity);
        var responseDto = ClienteDTO.translate(entity);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody @Validated ClienteDTO requestDto) {
        var entity = Cliente.translate(requestDto);
        var endereco = clienteService.findEnderecoById(requestDto.getEnderecoId());
        if (endereco == null)
            throw new IllegalArgumentException("Endereço informado não existe");

        var actualEntity = repository.findById(id);
        if (actualEntity.isEmpty())
            return ResponseEntity.notFound().build();
        entity.setId(id);
        entity = repository.save(entity);
        var responseDto = ClienteDTO.translate(entity);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ClienteDTO> delete(@PathVariable("id") Long id) {
        var entity = repository.findById(id);
        if (entity.isEmpty())
            return ResponseEntity.notFound().build();
        repository.delete(entity.get());
        return ResponseEntity.ok().build();
    }

}
