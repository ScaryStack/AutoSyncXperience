package com.taller.factura.repository;

import com.taller.factura.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    Factura findByIdCita(Integer idCita);
}
