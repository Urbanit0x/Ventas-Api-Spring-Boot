package com.clientes.ventas.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private LocalDate fecha_venta;

    private List<DetalleDTO> detalles;
}
