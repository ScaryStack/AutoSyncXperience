package com.taller.vehiculo.controller;

import com.taller.vehiculo.assembler.VehiculoModelAssembler;
import com.taller.vehiculo.model.Vehiculo;
import com.taller.vehiculo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v3.2/vehiculos")
public class VehiculoControllerV2 {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private VehiculoModelAssembler assembler;


    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Vehiculo>>getAllVehiculos(){
        List<EntityModel<Vehiculo>> carreras = vehiculoService.getAllVehiculos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(carreras,
                linkTo(methodOn(VehiculoControllerV2.class).getAllVehiculos()).withSelfRel());
    }


    @GetMapping(value ="/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Vehiculo> getVehiculoById(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
        return assembler.toModel(vehiculo);
    }


    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Vehiculo>> createVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo newVehiculo = vehiculoService.saveVehiculo(vehiculo);
        return ResponseEntity
                .created(linkTo(methodOn(VehiculoControllerV2.class).getVehiculoById(newVehiculo.getIdVehiculo())).toUri())
                .body(assembler.toModel(newVehiculo));
    }


    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Vehiculo>> updateVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        vehiculo.setIdVehiculo(id);
        Vehiculo updatedVehiculo = vehiculoService.saveVehiculo(vehiculo);
        return ResponseEntity
                .ok(assembler.toModel(updatedVehiculo));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteVehiculo(@PathVariable Integer id) {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}


















