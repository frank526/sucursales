package com.prueba.sucursales_inventario.adapter.out.persistence.producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    
    @Modifying
    @Transactional
    @Query("DELETE FROM ProductoEntity p WHERE p.id =?1 AND p.sucursal.id=?2")
    int deleteByProductIdAndSucursalId(Long productId, Long sucursalId);

    @Query("""
                SELECT p
                FROM ProductoEntity p
                WHERE p.sucursal.franquicia.id = ?1
                  AND p.stock = (
                      SELECT MAX(p2.stock)
                      FROM ProductoEntity p2
                      WHERE p2.sucursal.id = p.sucursal.id
                  )
            """)
    List<ProductoEntity> getMaxStockBySucursal(Long franquiciaId);


    
}