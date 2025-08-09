package com.prueba.sucursales_inventario.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.sucursales_inventario.application.servicio.franquicia.FranquiciaServicio;
import com.prueba.sucursales_inventario.domain.modelo.Franquicia;

@RestController
@RequestMapping("/api/franquicia")
public class FranquiciaController {

    private FranquiciaServicio franquiciaServicio;

    public FranquiciaController(FranquiciaServicio franquiciaServicio) {
        this.franquiciaServicio = franquiciaServicio;
    }

    @PostMapping
    public Franquicia crearFranquicia(@RequestParam String nombre){
       return franquiciaServicio.crearFranquicia(nombre);
    }



    
    
}
