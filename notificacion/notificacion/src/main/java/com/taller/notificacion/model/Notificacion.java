package com.taller.notificacion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "notificacion")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotification;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private Integer idCliente;
}