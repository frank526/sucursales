package com.prueba.sucursales_inventario.domain.modelo;

import java.util.List;

public class Franquicia {

    private Long id;

    private String nombre;

    private List<Sucursal> sucursalList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Franquicia(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Franquicia(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }
    
}
