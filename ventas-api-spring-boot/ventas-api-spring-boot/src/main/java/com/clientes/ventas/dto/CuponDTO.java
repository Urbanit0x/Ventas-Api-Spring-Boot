package com.clientes.ventas.dto;

import java.time.LocalDate;



import lombok.Data;

@Data
public class CuponDTO {
    private Integer idCupon;
    private String codigo;
    private Double descuento;  
    private LocalDate valido_hasta;
}
