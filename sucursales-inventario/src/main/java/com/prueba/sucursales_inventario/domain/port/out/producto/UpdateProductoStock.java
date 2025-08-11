package com.prueba.sucursales_inventario.domain.port.out.producto;

import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoEntity;
import com.prueba.sucursales_inventario.domain.modelo.Producto;

public interface UpdateProductoStock {

    ProductoEntity updateStock(Producto producto);
}
