package com.prueba.sucursales_inventario.domain.port.in.sucursal;

import com.prueba.sucursales_inventario.domain.modelo.Sucursal;

public interface CrearSucursalUseCase {

    Sucursal crearSucursal(Long franquiciaId, String nombre);

    
}
