package com.taller.historial.service;


import com.taller.historial.model.Historial;
import com.taller.historial.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    public List<Historial> getAllHistorial() {
        return historialRepository.findAll();
    }

    public Historial saveHistorial(Historial historial) {
        return historialRepository.save(historial);
    }

    public Historial getHistorialById(Integer id) {
        return historialRepository.findById(id).get();
    }

    public void deleteHistorialById(Integer id) {
        historialRepository.deleteById(id);
    }

    public Historial obtenerPorIdCita(Integer idCita){
        return historialRepository.findByIdCita(idCita);
    }

}
