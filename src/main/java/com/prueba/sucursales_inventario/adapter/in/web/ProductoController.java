package com.prueba.sucursales_inventario.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoDTO;
import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalDto;
import com.prueba.sucursales_inventario.application.servicio.producto.ProductoServicio;
import com.prueba.sucursales_inventario.domain.exception.EntityNotFoundException;
import com.prueba.sucursales_inventario.domain.modelo.Producto;
import com.prueba.sucursales_inventario.domain.modelo.Sucursal;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {


    private ProductoServicio productoServicio;

    public ProductoController(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @PostMapping
    public Producto crearSucursal(@RequestParam Long sucursalId, @RequestBody ProductoDTO productoDto) {

      String productoName = productoDto.getNombre();
      Integer stock = productoDto.getStock();

      return productoServicio.crearProducto(sucursalId, productoName, stock);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    
    
}
