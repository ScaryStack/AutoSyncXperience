package com.taller.servicio.service;

import com.taller.servicio.model.Servicio;
import com.taller.servicio.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public Servicio findById(Integer id) {
        return servicioRepository.findById(id).get();
    }

    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public void deleteById(Integer id) {
        servicioRepository.deleteById(id);
    }

    public List<Servicio> findByIdVehiculo(Integer idVehiculo) {
        return servicioRepository.findByIdVehiculo(idVehiculo);
    }

    public List<Servicio> findByIdDisponibilidad(Integer idDisponibilidad) {
        return servicioRepository.findByIdDisponibilidad(idDisponibilidad);
    }
}
