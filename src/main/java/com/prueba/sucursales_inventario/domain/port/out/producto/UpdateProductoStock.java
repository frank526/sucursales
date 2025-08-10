package com.prueba.sucursales_inventario.domain.port.out.producto;

import com.prueba.sucursales_inventario.domain.modelo.Producto;

public interface UpdateProductoStock {

    void updateStock(Producto producto);
}
