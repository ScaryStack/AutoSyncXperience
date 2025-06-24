package com.taller.vehiculo.controller;


import com.taller.vehiculo.model.Vehiculo;
import com.taller.vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Vehiculo>> obtenerPorIdCliente(@PathVariable Integer idCliente) {
        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosPorCliente(idCliente);
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculo(@PathVariable Integer id) {
        try {
            Vehiculo v = vehiculoService.getVehiculoById(id);
            return ResponseEntity.ok(v);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Vehiculo createVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.saveVehiculo(vehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo v = vehiculoService.getVehiculoById(id);
            v.setIdVehiculo(id);
            v.setPatente(vehiculo.getPatente());
            v.setMarca(vehiculo.getMarca());
            v.setModelo(vehiculo.getModelo());
            v.setColor(vehiculo.getColor());
            v.setIdCliente(vehiculo.getIdCliente());
            vehiculoService.saveVehiculo(v);
            return ResponseEntity.ok(v);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vehiculo> deleteVehiculo(@PathVariable Integer id) {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}
