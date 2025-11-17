package com.game.zone.service

import com.game.zone.model.Usuario
import com.game.zone.repository.UsuarioRepository

import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun getAllUsuarios(): List<Usuario> = usuarioRepository.findAll()

    fun getUsuarioById(id: Int): Usuario? =
        usuarioRepository.findById(id).orElse(null)

    fun getUsuarioByCorreo(correo: String): Usuario? =
        usuarioRepository.findByCorreo(correo)

    fun createUsuario(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    fun updateUsuario(id: Int, usuario: Usuario): Usuario? {
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.save(usuario.copy(id = id))
        } else null
    }

    fun deleteUsuario(id: Int): Boolean {
        return if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
            true
        } else false
    }

    fun existeCorreo(correo: String): Boolean =
        usuarioRepository.existsByCorreo(correo)
}