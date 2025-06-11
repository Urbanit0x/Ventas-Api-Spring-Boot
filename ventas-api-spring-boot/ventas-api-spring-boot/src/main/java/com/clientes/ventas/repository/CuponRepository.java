package com.clientes.ventas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientes.ventas.models.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Integer>{

    Optional<Cupon> findByCodigo(String codigo);
}
