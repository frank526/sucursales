package com.prueba.sucursales_inventario.domain.port.out.sucursal;

import com.prueba.sucursales_inventario.domain.modelo.Sucursal;

public interface LoadSucursal {

        Sucursal getSucursalByid(Long SucursalId);
    
}
