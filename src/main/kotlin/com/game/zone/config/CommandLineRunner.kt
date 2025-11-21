package com.game.zone.config

import com.game.zone.service.ProductoService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val productoService: ProductoService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        println("Inicializando datos de la aplicación...")
        productoService.inicializarProductos()
        println("Datos inicializados correctamente")
    }
}