package com.clientes.ventas.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.clientes.ventas.models.Vendedor;

public interface VendedorRepositorio extends JpaRepository<Vendedor, Integer> {

    
    

}
