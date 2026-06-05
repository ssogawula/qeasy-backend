package za.co.qeasy.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class AppSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(this::corsfSpec)
                .authorizeExchange(this::authorizeExchangeSpec)
                .build();
    }

    private ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchangeSpec(ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchangeSpec) {
        return authorizeExchangeSpec.pathMatchers("/api/v1/auth/**","/api/v1/users/register").permitAll()
                .anyExchange().authenticated();
    }


    private ServerHttpSecurity.CorsSpec corsfSpec(ServerHttpSecurity.CorsSpec csrfSpec) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        csrfSpec.configurationSource(source);
        return csrfSpec;
    }

}
