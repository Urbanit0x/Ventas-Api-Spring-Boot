package com.clientes.ventas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clientes.ventas.dto.VentaDTO;
import com.clientes.ventas.services.VentaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @PostMapping("/ventas")
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO dto){
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/ventas/{id}")
    public ResponseEntity<VentaDTO> obtener(@PathVariable Integer id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar(@PathVariable Integer id, @RequestBody VentaDTO dto){
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaDTO>> obtenerPorCliente(@PathVariable Integer clienteId){
        return ResponseEntity.ok(service.listarPorClienteId(clienteId));
    }

    
}
    

