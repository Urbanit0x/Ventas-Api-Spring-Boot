package com.clientes.ventas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.ventas.dto.CuponDTO;
import com.clientes.ventas.services.CuponService;

@RestController
@RequestMapping("/api/cupones")
public class CuponController {

    @Autowired
    private CuponService service;

    @PostMapping
    public ResponseEntity<CuponDTO> crear(@RequestBody CuponDTO dto){
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuponDTO> obtener(@PathVariable Integer id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuponDTO> actualizar(@PathVariable Integer id, @RequestBody CuponDTO dto){
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarCupon(@RequestBody CuponDTO cupon){
        boolean valido = service.validarCupon(cupon);
        return ResponseEntity.ok(valido);
    }
}
