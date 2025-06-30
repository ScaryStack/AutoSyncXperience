package com.taller.cliente;


import com.taller.cliente.model.Cliente;
import com.taller.cliente.repository.ClienteRepository;
import com.taller.cliente.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;


    @Test
    public void testFindAll() {
        Cliente cliente = crearCliente();
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.getAllClientes();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals(cliente.getIdCliente(), clientes.get(0).getIdCliente());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Cliente cliente = crearCliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente found = clienteService.getClienteById(id);
        assertNotNull(found);
        assertEquals(id, found.getIdCliente());
    }

    @Test
    public void testSave() {
        Cliente cliente = crearCliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente saved = clienteService.saveCliente(cliente);
        assertNotNull(saved);
        assertEquals(981329862, saved.getTelefono());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(clienteRepository).deleteById(id);

        clienteService.deleteCliente(id);
        verify(clienteRepository, times(1)).deleteById(id);
    }

    private Cliente crearCliente() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setPrNombre("Patricio");
        cliente.setSegNombre("Javier");
        cliente.setTerNombre("");
        cliente.setApPaterno("Jimenez");
        cliente.setApMaterno("Espinosa");
        cliente.setCorreo("p@gmail.com");
        cliente.setTelefono(981329862);

        return cliente;
    }
}
