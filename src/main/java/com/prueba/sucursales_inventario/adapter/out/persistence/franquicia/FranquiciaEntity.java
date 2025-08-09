package com.prueba.sucursales_inventario.adapter.out.persistence.franquicia;

import java.util.ArrayList;
import java.util.List;

import com.prueba.sucursales_inventario.adapter.out.persistence.sucursal.SucursalEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franquicias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FranquiciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;


    @OneToMany(mappedBy = "franquicia", cascade = CascadeType.ALL)
    private List<SucursalEntity> sucursales = new ArrayList<>();


    
}
