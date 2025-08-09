package com.prueba.sucursales_inventario.application.servicio.producto;

import java.util.Objects;

import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;
import com.prueba.sucursales_inventario.domain.port.in.producto.CrearProductoUseCase;
import com.prueba.sucursales_inventario.domain.port.out.producto.SaveProducto;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.LoadSucursal;

public class ProductoServicio implements CrearProductoUseCase {


    private final SaveProducto saveProducto;
    private final LoadSucursal loadSucursal;

    

    public ProductoServicio(SaveProducto saveProducto, LoadSucursal loadSucursal) {
        this.saveProducto = saveProducto;
        this.loadSucursal = loadSucursal;
    }



    @Override
    public Producto crearProducto(Long sucursalId, String nombre, Integer stock) {

        Producto producto = new Producto(nombre, stock);
        Sucursal sucursalFound = loadSucursal.getSucursalByid(sucursalId);

        if(Objects.isNull(sucursalFound)){
                    System.out.println("NO HAY PRODUCTO");

            throw new EntityNotFoundException("Sucursal con id " + sucursalId + " no existe");
        }
        return saveProducto.save(sucursalFound, producto);
       
    }
    
}
