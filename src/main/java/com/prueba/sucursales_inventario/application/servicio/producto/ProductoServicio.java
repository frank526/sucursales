package com.prueba.sucursales_inventario.application.servicio.producto;

import java.util.List;
import java.util.Objects;

import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoDTO;
import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoEntity;
import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.ProductoStockSucursal;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.in.producto.ActualizarStockUseCase;
import com.prueba.sucursales_inventario.domain.port.in.producto.ConsultaProductoStockUseCase;
import com.prueba.sucursales_inventario.domain.port.in.producto.CrearProductoUseCase;
import com.prueba.sucursales_inventario.domain.port.in.producto.EliminarProductoUseCase;
import com.prueba.sucursales_inventario.domain.port.out.producto.DeleteProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.LoadProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.SaveProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.UpdateProductoStock;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.LoadSucursal;

import jakarta.persistence.PersistenceException;

public class ProductoServicio implements CrearProductoUseCase, EliminarProductoUseCase, ActualizarStockUseCase, ConsultaProductoStockUseCase {


    private final SaveProducto saveProducto;
    private final LoadSucursal loadSucursal;
    private final DeleteProducto deleteProducto;
    private final UpdateProductoStock updateStock;
    private final LoadProducto loadProducto;

    

    public ProductoServicio(SaveProducto saveProducto, LoadSucursal loadSucursal, DeleteProducto deleteProducto, UpdateProductoStock updateStock, LoadProducto loadProducto) {
        this.saveProducto = saveProducto;
        this.loadSucursal = loadSucursal;
        this.deleteProducto = deleteProducto;
        this.updateStock = updateStock;
        this.loadProducto = loadProducto;
    }



    @Override
    public Producto crearProducto(Long sucursalId, String nombre, Integer stock) {

        Producto producto = new Producto(nombre, stock);
        Sucursal sucursalFound = loadSucursal.getSucursalByid(sucursalId);

        if(Objects.isNull(sucursalFound)){
            throw new EntityNotFoundException("Sucursal con id " + sucursalId + " no existe");
        }

        try{
            return saveProducto.save(sucursalFound, producto);
        }catch(Exception e){
            throw new PersistenceException("Error al guardar el producto "+e.getCause());
        }
        
       
    }



    @Override
    public void eliminarProducto(Long sucursalId, Long productoId) {
        int registrosEliminados =0;
        try{
           registrosEliminados= deleteProducto.delete(sucursalId, productoId);
        }catch(Exception e){
            throw new PersistenceException("Error al eliminar el producto "+e.getCause());
        }

        if(registrosEliminados==0){
            throw new EntityNotFoundException("No se encontro ningun producto");
        }

    }



    @Override
    public Producto actualizarStock(Producto producto) {
        ProductoEntity productoUpdated = null;

        try {
             productoUpdated = updateStock.updateStock(producto);

        } catch (Exception e) {
             throw new PersistenceException("Error al actualizar el producto "+e.getMessage());
        }

        return new Producto(productoUpdated.getId(), productoUpdated.getNombre(), productoUpdated.getStock());

    }



    @Override
    public List<ProductoStockSucursal> obtenerStockPorSucursal(Long franquiciaId) {

       List<ProductoStockSucursal> res = loadProducto.getMaxStock(franquiciaId);

       return res;
       

    }
    
}
