package com.taller.factura.controller;


import com.taller.factura.model.Factura;
import com.taller.factura.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v3/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> findAll() {
        return ResponseEntity.ok(facturaService.findAllFacturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> findById(@PathVariable Integer id) {
        try {
            Factura f = facturaService.findFacturaById(id);
            return ResponseEntity.ok(f);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cita/{idCita}")
    public ResponseEntity<Factura> findByIdCita(@PathVariable Integer idCita) {
        try {
            Factura f = facturaService.obtenerPorIdCita(idCita);
            return ResponseEntity.ok(f);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Factura> create(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.save(factura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> update(@PathVariable Integer id, @RequestBody Factura factura) {
        try {
            Factura f = facturaService.findFacturaById(id);
            f.setIdFactura(id);
            f.setMonto(factura.getMonto());
            f.setFechaEmision(factura.getFechaEmision());
            f.setMetodoPago(factura.getMetodoPago());
            f.setIdCita(factura.getIdCita());
            facturaService.save(f);
            return ResponseEntity.ok(f);
        }catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> delete(@PathVariable Integer id) {
        facturaService.deleteFactura(id);
        return ResponseEntity.noContent().build();

    }
}
