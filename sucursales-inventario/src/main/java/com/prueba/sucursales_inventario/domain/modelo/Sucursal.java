package com.prueba.sucursales_inventario.domain.modelo;

import java.util.List;

public class Sucursal {

    private Long id;

    private String nombre;

    private List<Producto> productoList;

    public Sucursal(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public Sucursal(Long id, String nombre, List<Producto> productoList) {
        this.id = id;
        this.nombre = nombre;
        this.productoList = productoList;
    }

    public Long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    
    
}
