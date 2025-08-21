package com.dipComputer.Dip.Computer.config;

import com.dipComputer.Dip.Computer.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Value("${admin.username:admin}")
        private String adminUsername;

        @Value("${admin.password:password}")
        private String adminPassword;

        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/api/admin/login").authenticated() // Authenticate
                                                                                                     // login requests
                                                .requestMatchers(HttpMethod.POST, "/api/products/**",
                                                                "/api/carousel-images/**",
                                                                "/api/reminders/**", "/api/accessories/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, "/api/products/**",
                                                                "/api/carousel-images/**",
                                                                "/api/reminders/**", "/api/accessories/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, "/api/products/**",
                                                                "/api/carousel-images/**",
                                                                "/api/reminders/**", "/api/accessories/**")
                                                .hasRole("ADMIN")
                                                .anyRequest().permitAll())
                                .httpBasic(org.springframework.security.config.Customizer.withDefaults()); // Enable
                                                                                                           // HTTP Basic
                                                                                                           // authentication
                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                var user = User.builder()
                                .username(adminUsername)
                                .password(passwordEncoder().encode(adminPassword))
                                .roles("ADMIN")
                                .build();
                return new InMemoryUserDetailsManager(user);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration
                                .setAllowedOrigins(Arrays.asList("http://localhost:5173",
                                                "https://dip-computer.netlify.app"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}
