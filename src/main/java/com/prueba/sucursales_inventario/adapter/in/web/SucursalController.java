package com.prueba.sucursales_inventario.adapter.in.web;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalDto;
import com.prueba.sucursales_inventario.application.servicio.franquicia.FranquiciaServicio;
import com.prueba.sucursales_inventario.application.servicio.sucursal.SucursalServicio;
import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Franquicia;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {


    private SucursalServicio sucursalServicio;

    public SucursalController(SucursalServicio sucursalServicio) {
        this.sucursalServicio = sucursalServicio;
    }

    @PostMapping
    public Sucursal crearSucursal(@RequestParam Long franquiciaId, @RequestBody SucursalDto sucursalDto) {


      String sucursalName = sucursalDto.getNombre();

      return sucursalServicio.crearSucursal(franquiciaId, sucursalName);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
}
