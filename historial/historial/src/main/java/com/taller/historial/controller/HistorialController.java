package com.taller.historial.controller;


import com.taller.historial.model.Historial;
import com.taller.historial.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/historiales")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping
    public List<Historial> getAll() {
        return historialService.getAllHistorial();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> getHistorialById(@PathVariable Integer id) {
        try{
            Historial h = historialService.getHistorialById(id);
            return ResponseEntity.ok(h);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cita/{idCita}")
    public ResponseEntity<Historial> findByIdCita(@PathVariable Integer idCita) {
        try {
            Historial h = historialService.obtenerPorIdCita(idCita);
            return ResponseEntity.ok(h);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Historial createHistorial(@RequestBody Historial historial) {
        return historialService.saveHistorial(historial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historial> updateHistorial(@PathVariable Integer id, @RequestBody Historial historial) {
        try{
            Historial h = historialService.getHistorialById(id);

            h.setIdHistorial(id);
            h.setDescripcion(historial.getDescripcion());
            h.setIdCita(historial.getIdCita());

            return ResponseEntity.ok(historialService.saveHistorial(h));
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Historial> deleteHistorial(@PathVariable Integer id) {

        historialService.deleteHistorialById(id);
        return ResponseEntity.noContent().build();

    }

}
