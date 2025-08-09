package com.prueba.sucursales_inventario.adapter.out.persistence.sucursal;


import java.util.ArrayList;
import java.util.List;

import com.prueba.sucursales_inventario.adapter.out.persistence.franquicia.FranquiciaEntity;
import com.prueba.sucursales_inventario.adapter.out.persistence.producto.ProductoEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "sucursales")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SucursalEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "franquicia_id", nullable = false) 
    private FranquiciaEntity franquicia;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<ProductoEntity> productos = new ArrayList<>();



    




    
}
