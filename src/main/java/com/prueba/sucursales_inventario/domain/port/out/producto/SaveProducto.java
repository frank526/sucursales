package com.prueba.sucursales_inventario.domain.port.out.producto;

import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;

public interface SaveProducto {

    Producto save(Sucursal sucursal, Producto producto);
    
}
