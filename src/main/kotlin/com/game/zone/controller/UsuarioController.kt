package com.game.zone.controller

import com.game.zone.model.Usuario
import com.game.zone.service.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val usuarioService: UsuarioService) {

    @GetMapping
    fun getAllUsuarios(): List<Usuario> = usuarioService.getAllUsuarios()

    @GetMapping("/{id}")
    fun getUsuarioById(@PathVariable id: Int): ResponseEntity<Usuario> {
        val usuario = usuarioService.getUsuarioById(id)
        return if (usuario != null) {
            ResponseEntity.ok(usuario)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/correo/{correo}")
    fun getUsuarioByCorreo(@PathVariable correo: String): ResponseEntity<Usuario> {
        val usuario = usuarioService.getUsuarioByCorreo(correo)
        return if (usuario != null) {
            ResponseEntity.ok(usuario)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUsuario(@RequestBody usuario: Usuario): ResponseEntity<Any> {
        if (usuarioService.existeCorreo(usuario.correo)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(mapOf("error" to "El correo ya está registrado"))
        }

        val nuevoUsuario = usuarioService.createUsuario(usuario)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario)
    }

    @PutMapping("/{id}")
    fun updateUsuario(@PathVariable id: Int, @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val usuarioActualizado = usuarioService.updateUsuario(id, usuario)
        return if (usuarioActualizado != null) {
            ResponseEntity.ok(usuarioActualizado)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUsuario(@PathVariable id: Int): ResponseEntity<Void> {
        return if (usuarioService.deleteUsuario(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}