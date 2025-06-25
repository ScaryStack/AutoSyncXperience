package com.taller.vehiculo.model;

import com.taller.vehiculo.repository.VehiculoRepository;
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
    private VehiculoRepository vehiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar vehiculos
        for (Integer i = 0; i < 50; i++) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setIdVehiculo(i + 1);
            vehiculo.setPatente(faker.vehicle().licensePlate());
            vehiculo.setColor(faker.vehicle().color());
            vehiculo.setMarca(faker.vehicle().manufacturer());
            vehiculo.setModelo(faker.vehicle().model());
            vehiculo.setIdCliente(faker.number().numberBetween(1,50));

            vehiculoRepository.save(vehiculo);
        }

        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
    }
}
