package com.prueba.sucursales_inventario.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.sucursales_inventario.application.servicio.franquicia.FranquiciaServicio;
import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.exception.PersistenceOperationException;
import com.prueba.sucursales_inventario.domain.modelo.Franquicia;

@RestController
@RequestMapping("/api/franquicia")
public class FranquiciaController {

    private FranquiciaServicio franquiciaServicio;

    public FranquiciaController(FranquiciaServicio franquiciaServicio) {
        this.franquiciaServicio = franquiciaServicio;
    }

    @PostMapping("/create")
    public ResponseEntity<?> crearFranquicia(@RequestParam String nombre) {
        Franquicia franquiciaNew = franquiciaServicio.crearFranquicia(nombre);

        return ResponseEntity.status(HttpStatus.CREATED).body(franquiciaNew);
    }


    @ExceptionHandler(PersistenceOperationException.class)
    public ResponseEntity<String> handleDBError(PersistenceOperationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }



    
    
}
