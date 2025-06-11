package com.clientes.ventas.dto;

import lombok.Data;

@Data
public class DetalleDTO {
    private Integer producto_id;

    private Integer cantidad;

    private Double precio_unitario;
}
