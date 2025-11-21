package com.game.zone.service


import com.game.zone.model.Producto
import com.game.zone.repository.ProductoRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class ProductoService(
    private val productoRepository: ProductoRepository
) {

    fun obtenerTodos(): List<Producto> {
        return productoRepository.findAll()
    }

    fun obtenerPorId(id: Int): Producto? {
        return productoRepository.findById(id).orElse(null)
    }

    fun obtenerPorCategoria(categoria: String): List<Producto> {
        return productoRepository.findByCategoria(categoria)
    }

    fun buscarPorNombre(nombre: String): List<Producto> {
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
    }

    fun crearProducto(producto: Producto): Producto {
        return try {
            productoRepository.save(producto)
        } catch (e: Exception) {
            throw IllegalArgumentException("Error al crear producto: ${e.message}")
        }
    }

    fun actualizarProducto(id: Int, producto: Producto): Producto? {
        return try {
            val productoExistente = productoRepository.findById(id).orElse(null)
            if (productoExistente != null) {
                val productoActualizado = productoExistente.copy(
                    nombre = producto.nombre,
                    precio = producto.precio,
                    descripcion = producto.descripcion,
                    categoria = producto.categoria
                )
                productoRepository.save(productoActualizado)
            } else {
                null
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("Error al actualizar producto: ${e.message}")
        }
    }

    fun eliminarProducto(id: Int): Boolean {
        return try {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("Error al eliminar producto: ${e.message}")
        }
    }

    @PostConstruct
    fun inicializarProductos() {
        if (productoRepository.count() == 0L) {
            val productos = listOf(
                Producto(
                    nombre = "Laptop Dell",
                    precio = 899.99,
                    descripcion = "Laptop de alto rendimiento con procesador Intel i7",
                    categoria = "Electrónica"
                ),
                Producto(
                    nombre = "Mouse Logitech",
                    precio = 29.99,
                    descripcion = "Mouse inalámbrico de precisión con 2.4GHz",
                    categoria = "Accesorios"
                ),
                Producto(
                    nombre = "Teclado Mecánico",
                    precio = 149.99,
                    descripcion = "Teclado mecánico RGB con switches Cherry MX",
                    categoria = "Accesorios"
                ),
                Producto(
                    nombre = "Monitor LG 27\"",
                    precio = 299.99,
                    descripcion = "Monitor 4K de 27 pulgadas con panel IPS",
                    categoria = "Electrónica"
                ),
                Producto(
                    nombre = "Audífonos Sony",
                    precio = 199.99,
                    descripcion = "Audífonos inalámbricos con cancelación activa de ruido",
                    categoria = "Accesorios"
                ),
                Producto(
                    nombre = "Cámara Web",
                    precio = 79.99,
                    descripcion = "Cámara web 1080p con micrófono integrado",
                    categoria = "Accesorios"
                ),
                Producto(
                    nombre = "SSD Samsung 1TB",
                    precio = 119.99,
                    descripcion = "Disco SSD NVMe de alta velocidad 980 Pro",
                    categoria = "Almacenamiento"
                ),
                Producto(
                    nombre = "Cable USB-C",
                    precio = 15.99,
                    descripcion = "Cable USB-C de carga rápida 100W compatible",
                    categoria = "Accesorios"
                )
            )
            productoRepository.saveAll(productos)
            println("Productos inicializados exitosamente")
        }
    }
}