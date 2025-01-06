/*
 * Copyright(c) 2015-2024 mirelplatform.
 */
package jp.vemi.mirel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import jp.vemi.framework.util.DatabaseUtil;
import jp.vemi.mirel.security.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${auth.method:jwt}")
    private String authMethod;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
        AuthenticationService authenticationService) throws Exception {
        DatabaseUtil.initializeDefaultTenant();
        
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/auth/**", "/api/**")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/check").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
            );

        if ("jwt".equals(authMethod) && authenticationService.isJwtSupported()) {
            http.oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt
                        .decoder(authenticationService.getJwtDecoder()))
                )
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        } else {
            http.formLogin(form -> form
                    .loginPage("/auth/login")
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/auth/logout")
                    .permitAll()
                );
        }

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("dev")
                .password(passwordEncoder.encode("dev"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("dev").password(passwordEncoder.encode("dev")).roles("USER");
        return authenticationManagerBuilder.build();
    }
}
