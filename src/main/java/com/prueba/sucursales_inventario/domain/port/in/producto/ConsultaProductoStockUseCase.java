package com.prueba.sucursales_inventario.domain.port.in.producto;

import java.util.List;

import com.prueba.sucursales_inventario.domain.modelo.ProductoStockSucursal;

public interface ConsultaProductoStockUseCase {

     List<ProductoStockSucursal> obtenerStockPorSucursal(Long franquiciaId);
}
