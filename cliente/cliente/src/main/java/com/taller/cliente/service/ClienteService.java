package com.taller.cliente.service;

import com.taller.cliente.model.Cliente;
import com.taller.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.getById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setPrNombre(clienteDetails.getPrNombre());
            cliente.setSegNombre(clienteDetails.getSegNombre());
            cliente.setTerNombre(clienteDetails.getTerNombre());
            cliente.setApPaterno(clienteDetails.getApPaterno());
            cliente.setApMaterno(clienteDetails.getApMaterno());
            cliente.setCorreo(clienteDetails.getCorreo());
            cliente.setTelefono(clienteDetails.getTelefono());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
    }

}
