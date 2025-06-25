package com.taller.cliente.model;

import com.taller.cliente.repository.ClienteRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar clientes
        for (Integer i = 0; i < 50; i++) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(i + 1);
            cliente.setNombres(faker.name().fullName());
            cliente.setApellidos(faker.name().lastName() + " " + faker.name().lastName());
            cliente.setCorreo(faker.internet().emailAddress());
            cliente.setTelefono(faker.number().numberBetween(100000000, 999999999));

            clienteRepository.save(cliente);
        }

        List<Cliente> clientes = clienteRepository.findAll();
    }
}