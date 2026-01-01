package com.hisami.hisami.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class AuthorizationServerConfig {

        @Bean
        @Order(1)
        public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
                        throws Exception {
                OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer
                                .authorizationServer();

                http
                                .cors(Customizer.withDefaults())
                                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                                .with(authorizationServerConfigurer, (authorizationServer) -> authorizationServer
                                                .oidc(Customizer.withDefaults()) // Enable OpenID Connect 1.0
                                )
                                .authorizeHttpRequests((authorize) -> authorize
                                                .anyRequest().authenticated())
                                .oauth2ResourceServer(resourceServer -> resourceServer
                                                .jwt(Customizer.withDefaults()))
                                .exceptionHandling((exceptions) -> exceptions
                                                .defaultAuthenticationEntryPointFor( // Se a requisição for feita por
                                                                                     // HTML, manda pro login
                                                                new LoginUrlAuthenticationEntryPoint("/login"),
                                                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML))
                                                .defaultAuthenticationEntryPointFor( // Se a requisição for feita por
                                                                                     // uma application/json,
                                                                                     // retorna 401
                                                                (request, response, authException) -> {
                                                                        response.setStatus(
                                                                                        HttpServletResponse.SC_UNAUTHORIZED);
                                                                        response.setContentType("application/json");
                                                                        response.getWriter().write(
                                                                                        "{\"error\": \"Unauthorized\"}");
                                                                },
                                                                new MediaTypeRequestMatcher(
                                                                                MediaType.APPLICATION_JSON)));

                return http.build();
        }

}
