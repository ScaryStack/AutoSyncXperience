package com.taller.disponibilidad.model;

import com.taller.disponibilidad.repository.DisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Profile("test")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Override
    public void run(String... args) throws Exception {
        //Estados de disponibilidad
        String[] estados = {
                "Disponible",
                "No disponible",
                "En preparación",
                "Cancelado",
                "En espera de confirmación",
                "Finalizado/Completado",
                "Reservado"
        };

        //Generar registros con distintos estados
        for (int i = 0; i < estados.length; i++) {
            Disponibilidad disponibilidad = new Disponibilidad();
            disponibilidad.setIdDisponibilidad(i + 1);
            disponibilidad.setEstado(estados[i]);
            disponibilidadRepository.save(disponibilidad);
        }

        List<Disponibilidad> disponibilidades = disponibilidadRepository.findAll();
    }
}

