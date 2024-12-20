package com.platzi.pizza.web.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    //en parte de lo que es el requestMatchers debes tener encuenta que el orden importa
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(customRequest ->{
                        customRequest
                                .requestMatchers("api/auth/**").permitAll()
                                .requestMatchers("/api/customers/**").hasAnyRole("ADMIN","CUSTOMER","WAITER")
                                .requestMatchers(HttpMethod.GET,"/api/pizzas/**").hasAnyRole("ADMIN","CUSTOMER","WAITER")
                                .requestMatchers(HttpMethod.POST,"/api/pizzas/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                                .requestMatchers("/api/orders/random").hasAuthority("random_order")
                                .requestMatchers(HttpMethod.GET,"/api/orders/**").hasAnyRole("WAITER","ADMIN")
                                .requestMatchers("/api/orders/**").hasRole("ADMIN");
                    })
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(Customizer.withDefaults())
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return  http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
