package com.prueba.sucursales_inventario.application.servicio.franquicia;

import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Franquicia;
import com.prueba.sucursales_inventario.domain.port.in.franquicia.CrearFranquiciaUseCase;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.LoadFranquicia;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.SaveFranquicia;

import jakarta.persistence.PersistenceException;

public class FranquiciaServicio implements CrearFranquiciaUseCase {


    private final SaveFranquicia saveFranquicia;

    private final LoadFranquicia loadFranquicia;


    public FranquiciaServicio(SaveFranquicia saveFranquicia, LoadFranquicia loadFranquicia) {
        this.saveFranquicia = saveFranquicia;
        this.loadFranquicia = loadFranquicia;
    }

    @Override
    public Franquicia crearFranquicia(String nombre) {
        Franquicia franquicia = new Franquicia(nombre);

        try{
            return saveFranquicia.save(franquicia);
        }catch(Exception e){
            throw new PersistenceException("Error al guardar la Franquicia", e.getCause());
        }
        
    }

    public Franquicia getFranquicia(Long id){
        return loadFranquicia.getFranquiciaByid(id);
    }
    
}
