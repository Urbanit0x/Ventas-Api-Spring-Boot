package com.clientes.ventas.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.ventas.dto.CuponDTO;
import com.clientes.ventas.models.Cupon;
import com.clientes.ventas.repository.CuponRepository;

@Service
public class CuponService {

    @Autowired
    private CuponRepository repository;

    public CuponDTO guardar(CuponDTO dto){
        Cupon cupon = toEntity(dto);
        Cupon saved = repository.save(cupon);
        return toDto(saved);
    }

    public List<CuponDTO> listar(){
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CuponDTO> obtenerPorId(Integer id){
        return  repository.findById(id)
                .map(this::toDto);
    }

    public Optional<CuponDTO> actualizar(Integer id, CuponDTO dto){
        return repository.findById(id).map(cupon ->{
            cupon.setIdCupon(dto.getIdCupon());
            cupon.setCodigo(dto.getCodigo());
            cupon.setDescuento(dto.getDescuento());
            cupon.setValido_hasta(dto.getValido_hasta());
            return toDto(repository.save(cupon));
        });
    }

    public boolean eliminar(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private CuponDTO toDto(Cupon cupon){
        CuponDTO dto = new CuponDTO();
        dto.setIdCupon(cupon.getIdCupon());
        dto.setCodigo(cupon.getCodigo());
        dto.setDescuento(cupon.getDescuento());
        dto.setValido_hasta(cupon.getValido_hasta());
        return dto;
    }

    private Cupon toEntity(CuponDTO dto){
        Cupon cupon = new Cupon();
        cupon.setIdCupon(dto.getIdCupon());
        cupon.setCodigo(dto.getCodigo());
        cupon.setDescuento(dto.getDescuento());
        cupon.setValido_hasta(dto.getValido_hasta());
        return cupon;
    }

    public boolean validarCupon(CuponDTO dto){
        return repository.findByCodigo(dto.getCodigo())
            .filter(c -> !c.getValido_hasta().isBefore(LocalDate.now()))
            .isPresent();
    }
}
