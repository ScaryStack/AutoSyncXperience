package com.taller.notificacion.repository;


import com.taller.notificacion.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    Notificacion finByIdCliente(Integer idCliente);
}
