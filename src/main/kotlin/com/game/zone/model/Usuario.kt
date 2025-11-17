package com.game.zone.model

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false, unique = true)
    val correo: String,

    @Column(nullable = false)
    val clave: String,

    @Column(nullable = false)
    val direccion: String,

    @Column(nullable = false)
    val aceptaterminos: Boolean = false,

    @Column(nullable = false, columnDefinition = "TEXT")
    val gustos: String,

    @Column(nullable = true)
    val imagen: String? = null
)
