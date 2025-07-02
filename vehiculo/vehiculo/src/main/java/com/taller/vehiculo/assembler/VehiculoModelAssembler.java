package com.taller.vehiculo.assembler;


import com.taller.vehiculo.controller.VehiculoControllerV2;
import com.taller.vehiculo.model.Vehiculo;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Component
public class VehiculoModelAssembler implements RepresentationModelAssembler<Vehiculo, EntityModel<Vehiculo>> {
    @Override
    public EntityModel<Vehiculo> toModel(Vehiculo vehiculo) {
        return EntityModel.of(vehiculo,
                linkTo(methodOn(VehiculoControllerV2.class).getVehiculoById(vehiculo.getIdVehiculo())).withSelfRel(),
                linkTo(methodOn(VehiculoControllerV2.class).getAllVehiculos()).withRel("vehiculos"));

    }
}
