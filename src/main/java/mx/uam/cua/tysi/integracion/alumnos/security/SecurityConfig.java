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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*")); // permite cualquier origen
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ CORS activado
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/Index.html", "/pais.html", "/css/**", "/js/**", "/assets/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pais/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/alumnos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/alumnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/alumnos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/alumnos/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/objetos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/objetos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/objetos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/objetos/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/entregas/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/entregas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/entregas/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});

        return http.build();
    }
}