package com.taller.historial.repository;

import com.taller.historial.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {
    Historial findByIdCita(Integer idCita);
}
