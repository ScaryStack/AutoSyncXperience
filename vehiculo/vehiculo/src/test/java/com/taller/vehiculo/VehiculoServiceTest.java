package com.taller.vehiculo;

import com.taller.vehiculo.model.Vehiculo;
import com.taller.vehiculo.repository.VehiculoRepository;
import com.taller.vehiculo.service.VehiculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class VehiculoServiceTest {

    @Autowired
    private VehiculoService vehiculoService;

    @MockBean
    private VehiculoRepository vehiculoRepository;

    @Test
    public void testFindAll() {
        Vehiculo vehiculo = crearVehiculo();
        when(vehiculoRepository.findAll()).thenReturn(List.of(vehiculo));

        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos();
        assertNotNull(vehiculos);
        assertEquals(1, vehiculos.size());
        assertEquals(vehiculo.getIdVehiculo(), vehiculos.get(0).getIdVehiculo());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Vehiculo vehiculo = crearVehiculo();
        when(vehiculoRepository.findById(id)).thenReturn(Optional.of(vehiculo));

        Vehiculo found = vehiculoService.getVehiculoById(id);
        assertNotNull(found);
        assertEquals(id, found.getIdVehiculo());
    }

    @Test
    public void testSave() {
        Vehiculo vehiculo = crearVehiculo();
        when(vehiculoRepository.save(vehiculo)).thenReturn(vehiculo);

        Vehiculo saved = vehiculoService.saveVehiculo(vehiculo);
        assertNotNull(saved);
        assertEquals("Red", saved.getColor());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(vehiculoRepository).deleteById(id);

        vehiculoService.deleteVehiculo(id);
        verify(vehiculoRepository, times(1)).deleteById(id);
    }


    private Vehiculo crearVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo(1);
        vehiculo.setMarca("Chevrolet");
        vehiculo.setModelo("Corsa");
        vehiculo.setColor("Red");
        vehiculo.setPatente("HFKS73");
        vehiculo.setIdCliente(1);
        return vehiculo;

    }

}