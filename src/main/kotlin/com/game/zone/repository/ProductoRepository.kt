package com.game.zone.repository

import com.game.zone.model.Producto
import org.springframework.data.jpa.repository.JpaRepository


interface ProductoRepository : JpaRepository<Producto, Int> {
    fun findByCategoria(categoria: String): List<Producto>
    fun findByNombreContainingIgnoreCase(nombre: String): List<Producto>
}