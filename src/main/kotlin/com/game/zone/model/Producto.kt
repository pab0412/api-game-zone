package com.game.zone.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "productos")
data class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val categoria: String,
    val fechaCreacion: LocalDateTime = LocalDateTime.now()
)
