package com.prueba.sucursales_inventario.application.servicio.producto;

import java.util.Objects;

import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.in.producto.ActualizarStockUseCase;
import com.prueba.sucursales_inventario.domain.port.in.producto.CrearProductoUseCase;
import com.prueba.sucursales_inventario.domain.port.in.producto.EliminarProductoUseCase;
import com.prueba.sucursales_inventario.domain.port.out.producto.DeleteProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.SaveProducto;
import com.prueba.sucursales_inventario.domain.port.out.producto.UpdateProductoStock;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.LoadSucursal;

public class ProductoServicio implements CrearProductoUseCase, EliminarProductoUseCase, ActualizarStockUseCase {


    private final SaveProducto saveProducto;
    private final LoadSucursal loadSucursal;
    private final DeleteProducto deleteProducto;
    private final UpdateProductoStock updateStock;

    

    public ProductoServicio(SaveProducto saveProducto, LoadSucursal loadSucursal, DeleteProducto deleteProducto, UpdateProductoStock updateStock) {
        this.saveProducto = saveProducto;
        this.loadSucursal = loadSucursal;
        this.deleteProducto = deleteProducto;
        this.updateStock = updateStock;
    }



    @Override
    public Producto crearProducto(Long sucursalId, String nombre, Integer stock) {

        Producto producto = new Producto(nombre, stock);
        Sucursal sucursalFound = loadSucursal.getSucursalByid(sucursalId);

        if(Objects.isNull(sucursalFound)){
            throw new EntityNotFoundException("Sucursal con id " + sucursalId + " no existe");
        }
        return saveProducto.save(sucursalFound, producto);
       
    }



    @Override
    public void eliminarProducto(Long sucursalId, Long productoId) {

        deleteProducto.delete(sucursalId, productoId);

    }



    @Override
    public void actualizarStock(Producto producto) {
      updateStock.updateStock(producto);

    }
    
}
