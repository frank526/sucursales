package com.prueba.sucursales_inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.prueba.sucursales_inventario.adapter.out.persistence.franquicia.FranquiciaPersistenceAdapter;
import com.prueba.sucursales_inventario.adapter.out.persistence.franquicia.FranquiciaRepository;
import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoPersistenceAdapter;
import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoRepository;
import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalPersistenceAdapter;
import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalRepository;
import com.prueba.sucursales_inventario.application.servicio.franquicia.FranquiciaServicio;
import com.prueba.sucursales_inventario.application.servicio.producto.ProductoServicio;
import com.prueba.sucursales_inventario.application.servicio.sucursal.SucursalServicio;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.LoadFranquicia;
import com.prueba.sucursales_inventario.domain.port.out.franquicia.SaveFranquicia;
import com.prueba.sucursales_inventario.domain.port.out.producto.SaveProducto;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.LoadSucursal;
import com.prueba.sucursales_inventario.domain.port.out.sucursal.SaveSucursal;

@SpringBootApplication
public class SucursalesInventarioApplication {


	@Bean
	public FranquiciaServicio franquiciaServicio(SaveFranquicia save, LoadFranquicia load){
		return new FranquiciaServicio(save, load);
	}

	@Bean
	public FranquiciaPersistenceAdapter franquiciaPersistenceAdapter(FranquiciaRepository repo){
		return new FranquiciaPersistenceAdapter(repo);
	}

	@Bean
	public SucursalServicio sucursalServicio(SaveSucursal save, LoadFranquicia loadFranquicia, LoadSucursal loadSucursal){
		return new SucursalServicio(save, loadFranquicia, loadSucursal);
	}


	@Bean
	public SucursalPersistenceAdapter sucursalPersistenceAdapter(SucursalRepository repo){
		return new SucursalPersistenceAdapter(repo);
	}


	@Bean
	public ProductoServicio productoServicio(SaveProducto save, LoadSucursal loadSucursal){
		return new ProductoServicio(save, loadSucursal);
	}

	@Bean
	public ProductoPersistenceAdapter productoPersistenceAdapter(ProductoRepository repo){
		return new ProductoPersistenceAdapter(repo);
	}






	public static void main(String[] args) {
		SpringApplication.run(SucursalesInventarioApplication.class, args);
	}

}
