package com.taller.notificacion.service;


import com.taller.notificacion.model.Notificacion;
import com.taller.notificacion.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    public Notificacion findById(Integer id) {
        return notificacionRepository.findById(id).get();
    }

    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void deleteById(Integer id) {
        notificacionRepository.deleteById(id);
    }

    public Notificacion encontrarPorIdCliente(Integer idCliente) {
        return notificacionRepository.findById(idCliente).get();
    }
}
