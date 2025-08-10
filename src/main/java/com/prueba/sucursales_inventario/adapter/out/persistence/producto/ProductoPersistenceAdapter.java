package com.prueba.sucursales_inventario.adapter.out.persistence.producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalEntity;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.ProductoStockSucursal;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.out.producto.DeleteProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.LoadProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.SaveProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.UpdateProductoStock;

public class ProductoPersistenceAdapter implements SaveProducto, DeleteProducto, UpdateProductoStock, LoadProducto {

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


    @Override
    public void delete(Long sucursalId, Long productoId) {

        try{
            productoRepository.deleteByProductIdAndSucursalId(productoId, sucursalId);

        }catch(Exception e){
            System.out.println("Error delete producto "+e);
        }

    }



    @Override
    public void updateStock(Producto producto) {

       Optional<ProductoEntity> productoFound = productoRepository.findById(producto.getId());

       if(productoFound.isPresent()){
        
       }

       ProductoEntity productoEntity = productoFound.get();

       productoEntity.setStock(producto.getStock());


        try{
            productoRepository.save(productoEntity);
        }catch(Exception e){
            System.out.println("ERROR TO UPDATE  "+e);
        }
        
    }



    @Override
    public List<ProductoStockSucursal> getMaxStock(Long franquiciaId) {
    
        List<ProductoEntity> resultados = productoRepository.getMaxStockBySucursal(franquiciaId);

        List<ProductoStockSucursal> prodList = resultados.stream().map(
                p -> new ProductoStockSucursal(p.getId(),
                        p.getNombre(),
                        p.getSucursal().getId(),
                        p.getSucursal().getNombre(),
                        p.getStock()))
                .collect(Collectors.toList());

            return prodList;
    }



    
}
