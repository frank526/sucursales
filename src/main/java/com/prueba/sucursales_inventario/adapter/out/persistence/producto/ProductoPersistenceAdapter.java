package com.prueba.sucursales_inventario.adapter.out.persistence.producto;

import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalEntity;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.out.producto.SaveProducto;

public class ProductoPersistenceAdapter implements SaveProducto {

    private final ProductoRepository productoRepository;

    

    public ProductoPersistenceAdapter(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }



    @Override
    public Producto save(Sucursal sucursal, Producto producto) {

        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setNombre(producto.getNombre());

        SucursalEntity sucursalEntity = new SucursalEntity();

        sucursalEntity.setId(sucursal.getId());
        sucursalEntity.setNombre(sucursal.getNombre());
        productoEntity.setSucursal(sucursalEntity);
        productoEntity.setStock(producto.getStock());

        ProductoEntity productoSaved=null;

        try{

             productoSaved = productoRepository.save(productoEntity);

        }catch(Exception e){

        }

        

        Producto productoModel = new Producto(productoSaved.getNombre(), productoSaved.getStock());

        return productoModel;

    }
    
}
