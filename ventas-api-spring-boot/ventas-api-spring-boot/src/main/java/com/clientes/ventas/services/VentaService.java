package com.clientes.ventas.services;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.ventas.dto.DetalleDTO;
import com.clientes.ventas.dto.VentaDTO;
import com.clientes.ventas.models.Detalle;
import com.clientes.ventas.models.Venta;
import com.clientes.ventas.repository.VentaRepository;

@Service
public class VentaService {


    @Autowired
    private VentaRepository repository;



    public VentaDTO guardar(VentaDTO dto){
        Venta venta = toEntity(dto);
        Venta saved = repository.save(venta);
        return toDTO(saved);
    }

    public List<VentaDTO> listar(){
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<VentaDTO> obtenerPorId(Integer id){
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<VentaDTO> actualizar(Integer id, VentaDTO dto){
        return repository.findById(id).map(venta ->{
            venta.setIdCliente(dto.getIdCliente());
            venta.setIdVendedor(dto.getIdVendedor());
            venta.setFecha_venta(dto.getFecha_venta());
            return toDTO(repository.save(venta));
        });
    }

    public boolean eliminar(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private VentaDTO toDTO(Venta venta){
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setIdCliente(venta.getIdCliente());
        dto.setIdVendedor(venta.getIdVendedor());
        dto.setFecha_venta(venta.getFecha_venta());

        if (venta.getDetalles() != null){
            List<DetalleDTO> detalles = venta.getDetalles().stream().map(det ->{
                DetalleDTO d = new DetalleDTO();
                d.setProducto_id(det.getProducto_id());
                d.setCantidad(det.getCantidad());
                d.setPrecio_unitario(det.getPrecio_unitario());
                return d;
            }).toList();
            dto.setDetalles(detalles);
        }   
        return dto;
    }

    private Venta toEntity(VentaDTO dto){
        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setIdCliente(dto.getIdCliente());
        venta.setIdVendedor(dto.getIdVendedor());
        venta.setFecha_venta(dto.getFecha_venta());

        if (dto.getDetalles() != null) {
            List<Detalle> detalles = dto.getDetalles().stream().map(d ->{
                Detalle det = new Detalle();
                det.setProducto_id(d.getProducto_id());
                det.setCantidad(d.getCantidad());
                det.setPrecio_unitario(d.getPrecio_unitario());
                det.setVenta(venta);
                return det;
            }).toList();
            venta.setDetalles(detalles);
        } 

        return venta;
    }

    

    public List<VentaDTO> listarPorClienteId(Integer idCliente){
        return repository.findById(idCliente).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
}
