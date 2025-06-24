package com.taller.cita.model;

import com.taller.cita.repository.CitaRepository;
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
    private CitaRepository citaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar citas
        for (Integer i = 0; i < 50; i++) {
            Cita cita = new Cita();
            cita.setIdCita(i + 1);

            String nombreCompleto = faker.name().fullName();
            String calle = faker.address().streetName();
            String ciudad = faker.address().city();
            String telefono = faker.phoneNumber().phoneNumber();
            String mensaje = "Estimado " + nombreCompleto + ",\n\n" +
                    "Le contactamos de la empresa AutoSyncXperience" + ".\n" +
                    "Nuestra dirección es " + calle + ", " + ciudad + ".\n" +
                    "Nuestro teléfono es " + telefono + ".\n\n" +
                    "Atentamente,\n" +
                    "El equipo de AutoSyncXperience";

            cita.setMensajeCita(mensaje);
            cita.setIdServicio(faker.number().numberBetween(1,5));

            citaRepository.save(cita);
        }

        List<Cita> citas = citaRepository.findAll();
    }
}
