package com.taller.vehiculo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Integer idCliente;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
}
