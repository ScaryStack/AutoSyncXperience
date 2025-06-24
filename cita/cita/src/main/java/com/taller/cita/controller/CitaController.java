package com.taller.cita.controller;

import com.taller.cita.model.Cita;
import com.taller.cita.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.getAllCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarCitaPorId(@PathVariable Integer id) {
        try{
            Cita c = citaService.getCitaById(id);
            return ResponseEntity.ok(c);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Cita createCita(@RequestBody Cita cita) {
        return citaService.saveCita(cita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCliente(@PathVariable Integer id, @RequestBody Cita cita) {
        try {

            Cita c = citaService.getCitaById(id);
            c.setIdCita(id);
            c.setMensajeCita(cita.getMensajeCita());
            c.setIdServicio(cita.getIdServicio());
            return ResponseEntity.ok(c);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}
