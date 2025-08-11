package com.prueba.sucursales_inventario.adapter.in.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.prueba.sucursales_inventario.domain.exception.PersistenceOperationException;
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

    @PostMapping("/create")
    public ResponseEntity<?> crearProducto(@RequestParam Long sucursalId, @RequestBody ProductoDTO productoDto) {

      String productoName = productoDto.getNombre();
      Integer stock = productoDto.getStock();

      Producto productpoNew = productoServicio.crearProducto(sucursalId, productoName, stock);

       return ResponseEntity.status(HttpStatus.CREATED).body(productpoNew);

    }

    @DeleteMapping("/delete")
    public  ResponseEntity<?> eliminarProducto(@RequestParam Long sucursalId, @RequestParam Long productId){
        productoServicio.eliminarProducto(sucursalId, productId);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El producto fue eliminado");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?>  actualizarProducto(@RequestBody ProductoDTO productoDto){

        Producto producto = new Producto(productoDto.getId(), productoDto.getStock());
        productoServicio.actualizarStock(producto);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El stock del producto fue actualizado");

        return ResponseEntity.ok(response);

    }

    @GetMapping("/stock")
    public ResponseEntity<?> obtenerMayorStock(@RequestParam Long franquiciaId) {

        List<ProductoStockSucursal> prodList = productoServicio.obtenerStockPorSucursal(franquiciaId);

        return ResponseEntity.status(HttpStatus.OK).body(prodList);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PersistenceOperationException.class)
    public ResponseEntity<String> handleDBError(PersistenceOperationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    
    
}
