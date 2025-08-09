package com.prueba.sucursales_inventario.domain.port.in.franquicia;

import com.prueba.sucursales_inventario.domain.modelo.Franquicia;

public interface CrearFranquiciaUseCase {

    Franquicia crearFranquicia(String nombre);
    
}
