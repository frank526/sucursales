package com.prueba.sucursales_inventario.adapter.out.persistence.producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.prueba.sucursales_inventario.adapter.out.persistence.franquicia.FranquiciaEntity;
import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalEntity;
import com.prueba.sucursales_inventario.domain.modelo.Franquicia;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.ProductoStockSucursal;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.out.producto.DeleteProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.LoadProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.SaveProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.UpdateProductoStock;

import jakarta.persistence.PersistenceException;

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

        ProductoEntity productoSaved = productoRepository.save(productoEntity);

        Producto productoModel = new Producto(productoSaved.getNombre(), productoSaved.getStock());
        return productoModel;

    }


    @Override
    public int delete(Long sucursalId, Long productoId) {
        return productoRepository.deleteByProductIdAndSucursalId(productoId, sucursalId);

    }

    @Override
    public ProductoEntity updateStock(Producto producto) {

        Optional<ProductoEntity> productoFound = productoRepository.findById(producto.getId());

        if (productoFound.isPresent()) {

        }

        ProductoEntity productoEntity = productoFound.get();
        productoEntity.setStock(producto.getStock());

        try {
           return productoRepository.save(productoEntity);
        } catch (Exception e) {
            System.out.println("ERROR TO UPDATE  " + e);
            return null;
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

    @Override
    public Producto getProductoByid(Long productoId) {

        Optional<ProductoEntity> res = productoRepository.findById(productoId);

        if (!res.isPresent()) {
            return null;
        }

        Producto producto = res.map(e -> new Producto(e.getId(), e.getNombre(), e.getStock())).orElseThrow();
        return producto;
    }



    
}
