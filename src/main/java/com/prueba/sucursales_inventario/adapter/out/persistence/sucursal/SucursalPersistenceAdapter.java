package com.prueba.sucursales_inventario.adapter.out.persistence.sucursal;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prueba.sucursales_inventario.adapter.out.persistence.franquicia.FranquiciaEntity;
import com.prueba.sucursales_inventario.adapter.out.persistence.franquicia.FranquiciaRepository;
import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoEntity;
import com.prueba.sucursales_inventario.domain.modelo.Franquicia;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.LoadFranquicia;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.LoadSucursal;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.SaveSucursal;

@Component
public class SucursalPersistenceAdapter implements SaveSucursal, LoadSucursal {


        private SucursalRepository sucursalRepository;


    
    public SucursalPersistenceAdapter(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }



    @Override
    public Sucursal save(Franquicia franquicia, Sucursal sucursal) {


        SucursalEntity sucursalEntity = new SucursalEntity();
        sucursalEntity.setNombre(sucursal.getNombre());

        FranquiciaEntity franquiciaEntity = new FranquiciaEntity();
        franquiciaEntity.setId(franquicia.getId());
        franquiciaEntity.setNombre(franquicia.getNombre());

        sucursalEntity.setFranquicia(franquiciaEntity);

        SucursalEntity sucursalSaved = sucursalRepository.save(sucursalEntity);

        Sucursal sucursalModel = new Sucursal(sucursalSaved.getId(), sucursalSaved.getNombre());

        return sucursalModel;
    }



    @Override
    public Sucursal getSucursalByid(Long SucursalId) {

         Optional<SucursalEntity> res = sucursalRepository.findById(SucursalId);

        if(!res.isPresent()){
            return null;
        }

        Sucursal sucursal = res.map(e-> new Sucursal(e.getId(), e.getNombre())).orElseThrow();
        return sucursal;
    }

    
}
