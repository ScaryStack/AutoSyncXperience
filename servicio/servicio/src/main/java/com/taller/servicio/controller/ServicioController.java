package com.taller.servicio.controller;


import com.taller.servicio.model.Servicio;
import com.taller.servicio.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getServicios() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Integer id) {
        try {
            Servicio s = servicioService.findById(id);
            return ResponseEntity.ok(s);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehiculo/{idVehiculo}")
    public ResponseEntity<List<Servicio>> getSeguimientoByVehiculo(@PathVariable Integer idVehiculo) {
        List<Servicio> vehiculos = servicioService.findByIdVehiculo(idVehiculo);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/disponibilidad/{idDisponibilidad}")
    public ResponseEntity<List<Servicio>> getSeguimientoByDisponibilidad(@PathVariable Integer idDisponibilidad) {
        List<Servicio> disponibilidades = servicioService.findByIdDisponibilidad(idDisponibilidad);
        return ResponseEntity.ok(disponibilidades);
    }

    @PostMapping
    public Servicio createServicio(@RequestBody Servicio servicio) {
        return servicioService.save(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Integer id, @RequestBody Servicio servicio) {
        try {
            Servicio s = servicioService.findById(id);
            s.setIdServicio(id);
            s.setTipoServicio(servicio.getTipoServicio());
            s.setIdVehiculo(servicio.getIdVehiculo());
            s.setIdDisponibilidad(servicio.getIdDisponibilidad());
            servicioService.save(s);
            return ResponseEntity.ok(s);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Servicio> deleteServicio(@PathVariable Integer id) {
        servicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
