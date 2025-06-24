package com.taller.disponibilidad.service;

import com.taller.disponibilidad.model.Disponibilidad;
import com.taller.disponibilidad.repository.DisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    public List<Disponibilidad> getAllDisponibilidades() {
        return disponibilidadRepository.findAll();
    }

    public Disponibilidad getDisponibilidadById(Integer id) {
        return disponibilidadRepository.findById(id).get();
    }

    public Disponibilidad saveDisponibilidad(Disponibilidad disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public void deleteDisponibilidad(Integer id) {
        disponibilidadRepository.deleteById(id);
    }
}
