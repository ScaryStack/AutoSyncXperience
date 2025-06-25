package com.taller.vehiculo.dto;

import com.taller.vehiculo.model.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDTO {

    private Vehiculo vehiculo;
    private ClienteDTO clienteDTO;
}
