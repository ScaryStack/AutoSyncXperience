package com.taller.factura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "factura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFactura;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private Integer idCita;

}