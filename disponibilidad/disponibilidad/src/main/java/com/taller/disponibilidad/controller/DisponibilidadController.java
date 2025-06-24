package com.taller.disponibilidad.controller;

import com.taller.disponibilidad.model.Disponibilidad;
import com.taller.disponibilidad.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/disponibilidad")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    @GetMapping
    public List<Disponibilidad> getAllDisponibilidades() {
        return disponibilidadService.getAllDisponibilidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidad> getDisponibilidadById(@PathVariable Integer id) {
        try{
            Disponibilidad d = disponibilidadService.getDisponibilidadById(id);
            return ResponseEntity.ok(d);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Disponibilidad createDisponibilidad(@RequestBody Disponibilidad disponibilidad) {
        return disponibilidadService.saveDisponibilidad(disponibilidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilidad> updateDisponibilidad(@PathVariable Integer id, @RequestBody Disponibilidad disponibilidad) {
        try {
            Disponibilidad d = disponibilidadService.getDisponibilidadById(id);
            d.setIdDisponibilidad(id);
            d.setEstado(disponibilidad.getEstado());
            disponibilidadService.saveDisponibilidad(d);
            return ResponseEntity.ok(d);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable Integer id) {
        disponibilidadService.deleteDisponibilidad(id);
        return ResponseEntity.noContent().build();
    }
}
