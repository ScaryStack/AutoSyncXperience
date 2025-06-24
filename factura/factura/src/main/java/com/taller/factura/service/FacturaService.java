package com.taller.factura.service;


import com.taller.factura.model.Factura;
import com.taller.factura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> findAllFacturas() {
        return facturaRepository.findAll();
    }

    public Factura findFacturaById(Integer id) {
        return facturaRepository.findById(id).get();
    }

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public void deleteFactura(Integer id) {
        facturaRepository.deleteById(id);
    }

    public Factura obtenerPorIdCita(Integer idCita) {
        return facturaRepository.findByIdCita(idCita);
    }
}
