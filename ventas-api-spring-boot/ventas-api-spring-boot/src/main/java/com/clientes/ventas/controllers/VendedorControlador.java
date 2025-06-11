package com.clientes.ventas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.clientes.ventas.dto.VendedorDTO;
import com.clientes.ventas.services.VendedorServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/vendedores")
public class VendedorControlador {

    @Autowired
    private VendedorServicio service;

    @PostMapping("/vendedores")
    public ResponseEntity<VendedorDTO> crear(@RequestBody VendedorDTO dto){
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/vendedores/{id}")
    public ResponseEntity<VendedorDTO> obtener(@PathVariable Integer id){
        return service.ObtenerPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/vendedores/{id}")
    public ResponseEntity<VendedorDTO> actualizar (@PathVariable Integer id, @RequestBody VendedorDTO dto){
        return service.actualizar(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); 
    }

    @GetMapping("/vendedores/sucursal/{id}")
    public ResponseEntity<List<VendedorDTO>> obtenerPorSucursal(@PathVariable Integer id) {
        List<VendedorDTO> vendedores = service.obtenerPorSucursal(id);
        return vendedores.isEmpty() 
            ? ResponseEntity.notFound().build() 
            : ResponseEntity.ok(vendedores);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id) 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.notFound().build();
    }
}

    
    

