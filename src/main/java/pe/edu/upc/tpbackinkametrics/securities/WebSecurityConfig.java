package pe.edu.upc.tpbackinkametrics.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import pe.edu.upc.tpbackinkametrics.serviceimplements.JwtUserDetailsService;

//@Profile(value = {"development", "production"})
//Actualmente está comentado(lo de la linea 24), entonces la seguridad siempre se aplica.
//
//        Pero lo que controla si necesitas token o no es esto (línea 73):
//
//        .anyRequest().permitAll()  // ← esto hace que TODO sea público, sin token


@Configuration
@EnableWebSecurity
// CÓDIGO COMENTADO (línea siguiente): activa @PreAuthorize en controllers para seguridad por roles. Descomentar para reactivar JWT.
// @EnableMethodSecurity(proxyTargetClass = true) // (1) para jwt funcione de nuevo, descomentar esto
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        // CÓDIGO COMENTADO (líneas 70-75): reglas originales JWT — definen qué endpoints son públicos y cuáles requieren autenticación. Descomentar y eliminar la línea .anyRequest().permitAll() para reactivar JWT.

                        // (2) para jwt funcione de nuevo, descomentar estas 3 lineas de abajo
                        // .requestMatchers("/InkaMetrics/tf/login", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**", "/error", "/usuarios").permitAll()
                        // .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        // .anyRequest().authenticated()

                        // AGREGADO: permite todos los endpoints sin autenticación para que el frontend Angular funcione sin token.
                .anyRequest().permitAll() // para jwt funcione de nuevo, Comentar esto (3) // ← esto hace que TODO sea público, sin token
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                // Mantenemos STATELESS porque es lo correcto para JWT, pero solo una vez
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // ⚠️ ESTA LÍNEA ES VITAL: Si no está, el token no se procesa nunca
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
