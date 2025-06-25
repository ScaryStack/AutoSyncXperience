package com.taller.vehiculo.service;

import com.taller.vehiculo.dto.ClienteDTO;
import com.taller.vehiculo.dto.RespuestaDTO;
import com.taller.vehiculo.model.Vehiculo;
import com.taller.vehiculo.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id).get();
    }

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public void deleteVehiculo(Integer id) {
        vehiculoRepository.deleteById(id);
    }

    public RespuestaDTO getClienteById(Integer id) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        Vehiculo vehiculo = new Vehiculo();
        vehiculo = vehiculoRepository.findById(id).get();

        ResponseEntity<ClienteDTO> responseEntity = restTemplate.getForEntity("http://localhost:8083/api/v3/clientes/" + vehiculo.getIdCliente(),
                ClienteDTO.class);

        ClienteDTO clienteDTO = responseEntity.getBody();

        respuestaDTO.setVehiculo(vehiculo);
        respuestaDTO.setClienteDTO(clienteDTO);

        return respuestaDTO;
    }
}
