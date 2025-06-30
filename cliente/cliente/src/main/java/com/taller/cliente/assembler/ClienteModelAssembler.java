package com.taller.cliente.assembler;

import com.taller.cliente.controller.ClienteControllerV2;
import com.taller.cliente.model.Cliente;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {
    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteControllerV2.class).getClienteById(cliente.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).getAllClientes()).withRel("clientes"));
    }
}