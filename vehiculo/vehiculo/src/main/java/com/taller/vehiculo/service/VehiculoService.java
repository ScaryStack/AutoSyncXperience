package com.taller.vehiculo.service;

import com.taller.vehiculo.model.Vehiculo;
import com.taller.vehiculo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> obtenerVehiculosPorCliente(Integer idCliente) {
        return vehiculoRepository.findByIdCliente(idCliente);
    }

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id).get();
    }

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public void deleteVehiculo(Integer id) {
        vehiculoRepository.deleteById(id);
    }
}
