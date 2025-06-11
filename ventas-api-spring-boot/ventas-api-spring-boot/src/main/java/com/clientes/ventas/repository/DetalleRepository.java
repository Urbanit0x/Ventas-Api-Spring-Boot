package com.clientes.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientes.ventas.models.Detalle;

public interface DetalleRepository extends JpaRepository<Detalle, Integer> {

}
