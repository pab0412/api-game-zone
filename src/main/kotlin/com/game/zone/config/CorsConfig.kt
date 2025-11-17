package com.game.zone.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        // Permitir todas las origenes
        config.allowedOrigins = listOf("*")

        // Métodos HTTP permitidos
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")

        // Headers permitidos
        config.allowedHeaders = listOf("*")
        return CorsFilter(source)
    }
}