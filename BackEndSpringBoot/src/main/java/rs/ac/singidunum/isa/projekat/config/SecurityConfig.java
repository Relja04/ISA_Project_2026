package rs.ac.singidunum.isa.projekat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // This registers your encoder with a strength of 12 into the Spring Container
        return new BCryptPasswordEncoder(12);
    }
}