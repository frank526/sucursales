package com.prueba.sucursales_inventario.adapter.out.persistence.producto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Integer stock;
    

    public ProductoDTO(Long id, String nombre, Integer stock){
        this.nombre = nombre;
        this.stock = stock;
        this.id = id;
    }

    

}
