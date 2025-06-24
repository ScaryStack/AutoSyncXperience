package com.taller.seguimiento.controller;


import com.taller.seguimiento.model.Seguimiento;
import com.taller.seguimiento.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/seguimientos")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    @GetMapping
    public List<Seguimiento> getSeguimientos() {
        return seguimientoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguimiento> getSeguimientoById(@PathVariable Integer id) {
        try {
            Seguimiento s = seguimientoService.findById(id);
            return ResponseEntity.ok(s);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Seguimiento>> obtenerPorIdCliente(@PathVariable Integer idCliente) {
        List<Seguimiento> clientes = seguimientoService.findByIdCliente(idCliente);
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public Seguimiento createSeguimiento(@RequestBody Seguimiento seguimiento) {
        return seguimientoService.save(seguimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seguimiento> updateSeguimiento(@PathVariable Integer id, @RequestBody Seguimiento seguimiento) {
        try {
            Seguimiento s = seguimientoService.findById(id);
            s.setIdSeguimiento(id);
            s.setFecha(seguimiento.getFecha());
            s.setDescripcion(seguimiento.getDescripcion());
            s.setIdCliente(seguimiento.getIdCliente());
            return ResponseEntity.ok(seguimientoService.save(s));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seguimiento> deleteSeguimiento(@PathVariable Integer id) {
        seguimientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

