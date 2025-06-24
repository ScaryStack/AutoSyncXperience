package com.taller.notificacion.controller;


import com.taller.notificacion.model.Notificacion;
import com.taller.notificacion.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v3/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> getAllNotificaciones() {
        return notificacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Integer id){
        try {
            Notificacion n = notificacionService.findById(id);
            return ResponseEntity.ok(n);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Notificacion> findByIdCliente(@PathVariable Integer idCliente){
        try {
            Notificacion n = notificacionService.encontrarPorIdCliente(idCliente);
            return ResponseEntity.ok(n);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Notificacion createNotificacion(@RequestBody Notificacion notificacion){
        return notificacionService.save(notificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> updateNotificacion(@PathVariable Integer id, @RequestBody Notificacion notificacion){
        try{
            Notificacion n = notificacionService.findById(id);
            n.setIdNotification(id);
            n.setMensaje(notificacion.getMensaje());
            n.setIdCliente(notificacion.getIdCliente());

            return ResponseEntity.ok(notificacionService.save(n));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notificacion> deleteById(@PathVariable Integer id){
        notificacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
