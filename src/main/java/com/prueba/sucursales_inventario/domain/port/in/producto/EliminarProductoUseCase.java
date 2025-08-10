package com.prueba.sucursales_inventario.domain.port.in.producto;

public interface EliminarProductoUseCase {

    void eliminarProducto(Long sucursalId, Long productoId);
    
}
