package com.prueba.sucursales_inventario.application.servicio.sucursal;

import java.util.Objects;

import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Franquicia;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.in.sucursal.CrearSucursalUseCase;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.LoadFranquicia;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.LoadSucursal;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.SaveSucursal;

public class SucursalServicio implements CrearSucursalUseCase {


    private final SaveSucursal saveSucursal;
    private final LoadFranquicia loadFranquicia;
    private final LoadSucursal loadSucursal;

    public SucursalServicio(SaveSucursal saveSucursal, LoadFranquicia loadFranquicia, LoadSucursal loadSucursal){
        this.saveSucursal = saveSucursal;
        this.loadFranquicia = loadFranquicia;
        this.loadSucursal = loadSucursal;
    }


    @Override
    public Sucursal crearSucursal(Long franquiciaId, String nombre) {
        Sucursal sucursal = new Sucursal(nombre);

        Franquicia franquiciaFound = loadFranquicia.getFranquiciaByid(franquiciaId);

        if (Objects.isNull(franquiciaFound)) {

            throw new EntityNotFoundException("Franquicia con id " + franquiciaId + " no existe");
        }

        return saveSucursal.save(franquiciaFound, sucursal);
    }


    public Sucursal getSucursal(Long id) {
        return loadSucursal.getSucursalByid(id);
    }
    
}
