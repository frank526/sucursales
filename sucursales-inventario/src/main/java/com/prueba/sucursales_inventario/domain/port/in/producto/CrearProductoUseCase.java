package com.prueba.sucursales_inventario.domain.port.in.producto;

import com.prueba.sucursales_inventario.domain.modelo.Producto;

public interface CrearProductoUseCase {

    Producto crearProducto(Long sucursalId, String nombre, Integer stock);

}
