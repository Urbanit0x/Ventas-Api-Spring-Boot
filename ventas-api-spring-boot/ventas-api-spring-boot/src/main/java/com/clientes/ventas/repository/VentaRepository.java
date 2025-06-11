package com.clientes.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientes.ventas.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer>{

    List<Venta> findByIdCliente(Integer idCliente);

}
