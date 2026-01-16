package com.github.computerhuis.portaal.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("${security.rememberme.key}")
    private String rememberMeKey;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http, final DataSource dataSource) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/jakarta.faces.resource/**", "/login.faces").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.faces")
                .defaultSuccessUrl("/index.faces", true) // ✅ redirect here on success
                .permitAll()
                .failureUrl("/login.faces?error=true")
            )
            .rememberMe(rm -> rm
                .rememberMeParameter("remember-me") // must match checkbox name
                .tokenValiditySeconds(691_200) // 8 days
                .key(rememberMeKey) // injected
                .userDetailsService(jdbcUserDetailsService(dataSource))
            )
            .logout(logout -> logout
                .logoutUrl("/logout.faces")
                .logoutSuccessUrl("/login.faces")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
            )
            .csrf(csrf -> csrf.disable()); // Optional, JSF already has CSRF protection

        return http.build();
    }


    @Bean
    public UserDetailsService jdbcUserDetailsService(final DataSource dataSource) {
        val manager = new JdbcUserDetailsManager(dataSource);

        // Default queries if your table names differ:
        manager.setUsersByUsernameQuery(
            "SELECT id, passhash, CASE WHEN unregistered IS NULL THEN 'true' else 'false' END AS enabled FROM persons WHERE id = ?"
        );
        manager.setAuthoritiesByUsernameQuery(
            "SELECT person_id, authority FROM person_roles WHERE person_id = ?"
        );

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
