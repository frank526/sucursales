package com.prueba.sucursales_inventario.adapter.out.persistence.franquicia;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prueba.sucursales_inventario.domain.modelo.Franquicia;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.LoadFranquicia;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.SaveFranquicia;

@Component
public class FranquiciaPersistenceAdapter implements SaveFranquicia, LoadFranquicia  {

    private FranquiciaRepository franquiciaRepository;

    

    public FranquiciaPersistenceAdapter(FranquiciaRepository franquiciaRepository) {
        this.franquiciaRepository = franquiciaRepository;
    }



    @Override
    public Franquicia save(Franquicia franquicia) {

        FranquiciaEntity franquiciaEntity = new FranquiciaEntity();

        franquiciaEntity.setNombre(franquicia.getNombre());

        FranquiciaEntity franquiciaSaved = franquiciaRepository.save(franquiciaEntity);

        Franquicia franquiciaModel = new Franquicia(franquiciaSaved.getNombre());

        return franquiciaModel;
    }



    @Override
    public Franquicia getFranquiciaByid(Long franquiciaId) {

        Optional<FranquiciaEntity> res = franquiciaRepository.findById(franquiciaId);

        if(!res.isPresent()){
            return null;
        }

        Franquicia franquicia = res.map(e-> new Franquicia(e.getId(), e.getNombre())).orElseThrow();

        return franquicia;

    }

}
