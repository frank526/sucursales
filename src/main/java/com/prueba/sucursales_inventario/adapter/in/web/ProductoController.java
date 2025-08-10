package com.prueba.sucursales_inventario.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoDTO;
import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalDto;
import com.prueba.sucursales_inventario.application.servicio.producto.ProductoServicio;
import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.ProductoStockSucursal;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {


    private ProductoServicio productoServicio;

    public ProductoController(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @PostMapping
    public Producto crearProducto(@RequestParam Long sucursalId, @RequestBody ProductoDTO productoDto) {

      String productoName = productoDto.getNombre();
      Integer stock = productoDto.getStock();

      return productoServicio.crearProducto(sucursalId, productoName, stock);

    }

    @DeleteMapping
    public String eliminarProducto(@RequestParam Long sucursalId, @RequestParam Long productId){

        productoServicio.eliminarProducto(sucursalId, productId);
        return "eliminado";
        
    }

    @PutMapping
    public String actualizarProducto(@RequestBody ProductoDTO productoDto){

        Producto producto = new Producto(productoDto.getId(), productoDto.getStock());
        productoServicio.actualizarStock(producto);
        return "actualizado";

    }

    @GetMapping
    public List<ProductoStockSucursal> obtenerMayorStock(@RequestParam Long franquiciaId){

       return productoServicio.obtenerStockPorSucursal(franquiciaId);
        
    } 

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    
    
}
