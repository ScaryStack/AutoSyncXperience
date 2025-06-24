package com.taller.seguimiento.service;


import com.taller.seguimiento.model.Seguimiento;
import com.taller.seguimiento.repository.SeguimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    public List<Seguimiento> findAll() {
        return seguimientoRepository.findAll();
    }

    public Seguimiento save(Seguimiento seguimiento) {
        return seguimientoRepository.save(seguimiento);
    }

    public Seguimiento findById(Integer id) {
        return seguimientoRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        seguimientoRepository.deleteById(id);
    }

    public List<Seguimiento> findByIdCliente(Integer idCliente) {
        return seguimientoRepository.findByIdCliente(idCliente);
    }
}
