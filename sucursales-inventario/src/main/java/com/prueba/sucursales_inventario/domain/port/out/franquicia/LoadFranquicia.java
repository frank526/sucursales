package com.prueba.sucursales_inventario.domain.port.out.franquicia;

import com.prueba.sucursales_inventario.domain.modelo.Franquicia;

public interface LoadFranquicia {

    Franquicia getFranquiciaByid(Long franquiciaId);
    
}
