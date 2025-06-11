package com.clientes.ventas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.ventas.dto.VendedorDTO;
import com.clientes.ventas.models.Vendedor;
import com.clientes.ventas.repository.VendedorRepositorio;




@Service
public class VendedorServicio {

    @Autowired
    private VendedorRepositorio repository;

    public VendedorDTO guardar(VendedorDTO dto){
        Vendedor vendedor = toEntity(dto);
        Vendedor saved = repository.save(vendedor);
        return toDTO(saved);
    }

    public List<VendedorDTO> listar(){
        return repository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public Optional<VendedorDTO> ObtenerPorId (Integer id){
        return repository.findById(id)
            .map(this::toDTO);
    }

    public Optional<VendedorDTO> actualizar(Integer id, VendedorDTO dto) {
        return repository.findById(id)
            .map(vendedor -> {
                vendedor.setIdUsuario(dto.getIdUsuario());
                vendedor.setNombreCompleto(dto.getNombreCompleto());
                vendedor.setRut(dto.getRut());
                vendedor.setAreaVentas(dto.getAreaVentas());
                return toDTO(repository.save(vendedor));
            });
    }

    public boolean eliminar(Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private VendedorDTO toDTO(Vendedor vendedor) {
        VendedorDTO dto = new VendedorDTO();
        dto.setIdVendedor(vendedor.getIdVendedor());
        dto.setIdUsuario(vendedor.getIdUsuario());
        dto.setNombreCompleto(vendedor.getNombreCompleto());
        dto.setRut(vendedor.getRut());
        dto.setAreaVentas(vendedor.getAreaVentas());
        return dto;
    }

    private Vendedor toEntity(VendedorDTO dto) {
        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(dto.getIdVendedor());
        vendedor.setIdUsuario(dto.getIdUsuario());
        vendedor.setNombreCompleto(dto.getNombreCompleto());
        vendedor.setRut(dto.getRut());
        vendedor.setAreaVentas(dto.getAreaVentas());
        return vendedor;
    }

    public List<VendedorDTO> obtenerPorSucursal(Integer id) {
        return repository.findById(id).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
