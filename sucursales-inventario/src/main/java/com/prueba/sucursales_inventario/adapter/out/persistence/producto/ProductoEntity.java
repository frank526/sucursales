package com.prueba.sucursales_inventario.adapter.out.persistence.producto;

import com.prueba.sucursales_inventario.adapter.out.persistence.franquicia.FranquiciaEntity;
import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class ProductoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false) 
    private SucursalEntity sucursal;


    
}
