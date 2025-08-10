package com.prueba.sucursales_inventario.adapter.out.persistence.producto;

import lombok.Data;

@Data
public class ProductoDTO {

    private String nombre;
    private Integer stock;
    private Long id;

}
