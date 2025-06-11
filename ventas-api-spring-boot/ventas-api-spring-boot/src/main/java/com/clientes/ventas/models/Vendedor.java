package com.clientes.ventas.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "vendedores")
@Data
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idVendedor;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String areaVentas;

}
