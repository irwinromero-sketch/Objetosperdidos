package mx.uam.cua.tysi.integracion.alumnos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // ADMIN — puede hacer todo
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        // USER — solo puede consultar (GET)
        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Recursos estáticos — acceso libre
                        .requestMatchers("/", "/index.html", "/pais.html", "/css/**", "/js/**", "/assets/**").permitAll()

                        // APIs externas — acceso libre
                        .requestMatchers(HttpMethod.GET, "/pais/**").permitAll()

                        // Alumnos — ADMIN puede todo, USER solo GET
                        .requestMatchers(HttpMethod.GET, "/alumnos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/alumnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/alumnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/alumnos/**").hasRole("ADMIN")

                        // Objetos — ADMIN puede todo, USER solo GET
                        .requestMatchers(HttpMethod.GET, "/objetos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/objetos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/objetos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/objetos/**").hasRole("ADMIN")

                        // Entregas — ADMIN puede todo, USER solo GET
                        .requestMatchers(HttpMethod.GET, "/entregas/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/entregas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/entregas/**").hasRole("ADMIN")

                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});

        return http.build();
    }
}