package com.prueba.sucursales_inventario.domain.port.out.producto;

public interface DeleteProducto {

   int delete(Long sucursalId, Long productoId);
    
}
