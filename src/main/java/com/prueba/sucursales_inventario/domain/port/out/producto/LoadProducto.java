package com.prueba.sucursales_inventario.domain.port.out.producto;

import java.util.List;

import com.prueba.sucursales_inventario.domain.modelo.ProductoStockSucursal;

public interface LoadProducto {

    List<ProductoStockSucursal> getMaxStock(Long franquiciaId);
}
