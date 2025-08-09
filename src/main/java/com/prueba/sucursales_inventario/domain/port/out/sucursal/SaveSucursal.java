package com.prueba.sucursales_inventario.domain.port.out.sucursal;

import com.prueba.sucursales_inventario.domain.modelo.Franquicia;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;

public interface SaveSucursal {

     Sucursal save(Franquicia franquicia, Sucursal sucursal);

    
}
