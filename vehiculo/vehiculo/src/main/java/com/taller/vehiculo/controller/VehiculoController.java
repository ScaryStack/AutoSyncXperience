package com.taller.vehiculo.controller;

import com.taller.vehiculo.model.Vehiculo;
import com.taller.vehiculo.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/vehiculos")
@Tag(name = "Vehiculos", description = "Operaciones relacionadas con los vehiculos")

public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    @Operation(summary = "Obtener todos los vehiculos", description = "Obtiene una lista de todos los vehiculos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class)))
    })
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoService.getAllVehiculos();
    }



    @PostMapping
    @Operation(summary = "Crear un nuevo Vehiculo", description = "Crea un nuevo vehiculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class)))
    })

    public Vehiculo createVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.saveVehiculo(vehiculo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un vehiculo", description = "Actualiza un vehiculo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vehiculo.class))),
            @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    })
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
    @Operation(summary = "Eliminar un vehiculo", description = "Elimina un vehiculo por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehiculo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Vehiculo no encontrado")
    })

    public ResponseEntity<Vehiculo> deleteVehiculo(@PathVariable Integer id) {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}
