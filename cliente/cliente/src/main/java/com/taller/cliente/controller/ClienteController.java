package com.taller.cliente.controller;


import com.taller.cliente.model.Cliente;
import com.taller.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id) {
        try{
            Cliente c = clienteService.getClienteById(id);
            return ResponseEntity.ok(c);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            Cliente c = clienteService.getClienteById(id);
            c.setIdCliente(id);
            c.setPrNombre(cliente.getPrNombre());
            c.setSegNombre(cliente.getSegNombre());
            c.setTerNombre(cliente.getTerNombre());
            c.setApPaterno(cliente.getApPaterno());
            c.setApMaterno(cliente.getApMaterno());
            c.setTelefono(cliente.getTelefono());
            c.setCorreo(cliente.getCorreo());
            return ResponseEntity.ok(c);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
