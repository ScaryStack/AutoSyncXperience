package com.taller.servicio.repository;

import com.taller.servicio.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    List<Servicio> findByIdVehiculo(Integer idVehiculo);
    List<Servicio> findByIdDisponibilidad(Integer idDisponibilidad);
}
