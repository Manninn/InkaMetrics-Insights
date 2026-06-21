package pe.edu.upc.tpbackinkametrics.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

// AGREGADO: habilita CORS para que el frontend Angular (localhost:4200) pueda consumir el backend sin ser bloqueado por el navegador.
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200", "https://frontend-inkametricks.onrender.com")); // origen permitido: frontend Angular local y producción
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // métodos HTTP permitidos (OPTIONS es requerido para preflight)
        config.setAllowedHeaders(List.of("*")); // acepta cualquier cabecera (Authorization, Content-Type, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // aplica a todos los endpoints
        return new CorsFilter(source);
    }
}
