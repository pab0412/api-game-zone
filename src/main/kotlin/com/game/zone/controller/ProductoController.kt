package com.game.zone.controller

import com.game.zone.model.Producto
import com.game.zone.service.ProductoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/productos")
class ProductoController(
    private val productoService: ProductoService
) {

    @GetMapping
    fun obtenerTodos(): ResponseEntity<List<Producto>> {
        return try {
            val productos = productoService.obtenerTodos()
            ResponseEntity.ok(productos)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): ResponseEntity<Producto> {
        return try {
            val producto = productoService.obtenerPorId(id)
            if (producto != null) {
                ResponseEntity.ok(producto)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/categoria/{categoria}")
    fun obtenerPorCategoria(@PathVariable categoria: String): ResponseEntity<List<Producto>> {
        return try {
            val productos = productoService.obtenerPorCategoria(categoria)
            ResponseEntity.ok(productos)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @GetMapping("/buscar/{nombre}")
    fun buscarPorNombre(@PathVariable nombre: String): ResponseEntity<List<Producto>> {
        return try {
            val productos = productoService.buscarPorNombre(nombre)
            ResponseEntity.ok(productos)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @PostMapping
    fun crearProducto(@RequestBody producto: Producto): ResponseEntity<Producto> {
        return try {
            val nuevoProducto = productoService.crearProducto(producto)
            ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
    }

    @PutMapping("/{id}")
    fun actualizarProducto(
        @PathVariable id: Int,
        @RequestBody producto: Producto
    ): ResponseEntity<Producto> {
        return try {
            val productoActualizado = productoService.actualizarProducto(id, producto)
            if (productoActualizado != null) {
                ResponseEntity.ok(productoActualizado)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarProducto(@PathVariable id: Int): ResponseEntity<Void> {
        return try {
            val eliminado = productoService.eliminarProducto(id)
            if (eliminado) {
                ResponseEntity.noContent().build()
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}