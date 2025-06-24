package com.taller.servicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicio;

    @Column(nullable = false)
    private String tipoServicio;

    @Column(nullable = false)
    private Integer idVehiculo;

    @Column(nullable = false)
    private Integer idDisponibilidad;

}
