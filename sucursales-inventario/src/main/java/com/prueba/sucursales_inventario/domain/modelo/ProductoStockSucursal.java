package com.prueba.sucursales_inventario.domain.modelo;

public class ProductoStockSucursal {

    private Long productoId;
    private String nombreProducto;
    private Long sucursalId;
    private String nombreSucursal;
    private Integer stock;


    public ProductoStockSucursal(Long productoId, String nombreProducto, Long sucursalId, String nombreSucursal,
            Integer stock) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.sucursalId = sucursalId;
        this.nombreSucursal = nombreSucursal;
        this.stock = stock;
    }


    public Long getProductoId() {
        return productoId;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }


    public Long getSucursalId() {
        return sucursalId;
    }


    public String getNombreSucursal() {
        return nombreSucursal;
    }


    public Integer getStock() {
        return stock;
    }


    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }


    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }


    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }


    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }


    public void setStock(Integer stock) {
        this.stock = stock;
    }

    

    

    
    
}
