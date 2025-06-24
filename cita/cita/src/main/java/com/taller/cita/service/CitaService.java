package com.taller.cita.service;

import com.taller.cita.model.Cita;
import com.taller.cita.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Cita getCitaById(Integer id) {
        return citaRepository.findById(id).get();
    }

    public Cita saveCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public void deleteCita(Integer id) {
        citaRepository.deleteById(id);
    }

}
