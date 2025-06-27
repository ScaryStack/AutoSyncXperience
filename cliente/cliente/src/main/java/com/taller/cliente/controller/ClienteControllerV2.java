package com.taller.cliente.controller;

import com.taller.cliente.assembler.ClienteModelAssembler;
import com.taller.cliente.model.Cliente;
import com.taller.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v3.5/clientes")
public class ClienteControllerV2 {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cliente>> getAllClientes() {
        List<EntityModel<Cliente>> carreras = clienteService.getAllClientes().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(carreras,
                linkTo(methodOn(ClienteControllerV2.class).getAllClientes()).withSelfRel());
    }
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Cliente> getClienteById(@PathVariable Integer id) {
        Cliente cliente = clienteService.getClienteById(id);
        return assembler.toModel(cliente);
    }
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> createCliente(@RequestBody Cliente cliente) {
        Cliente newCliente = clienteService.saveCliente(cliente);
        return ResponseEntity
                .created(linkTo(methodOn(ClienteControllerV2.class).getClienteById(newCliente.getIdCliente())).toUri())
                .body(assembler.toModel(newCliente));
    }
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setIdCliente(id);
        Cliente updatedCliente = clienteService.saveCliente(cliente);
        return ResponseEntity
                .ok(assembler.toModel(updatedCliente));
    }
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
