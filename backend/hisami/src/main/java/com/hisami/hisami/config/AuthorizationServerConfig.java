package com.hisami.hisami.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
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

        @Bean
        @Order(2)
        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
                        throws Exception {
                http
                                .httpBasic(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable()) // Desativa CSRF para facilitar testes com POST
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests((authorize) -> authorize
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                                .requestMatchers("/auth/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login") // sua página
                                                .loginProcessingUrl("/login") // endpoint que o Spring usa para
                                                                              // processar o POST
                                                .defaultSuccessUrl("/", true)
                                                .failureUrl("/login?error=true")
                                                .permitAll())
                                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

                return http.build();
        }

        // @Bean
        // public UserDetailsService userDetailsService() {
        // return new CustomUserDetailsService();
        // }
        // @Bean
        // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
        // {
        // UserDetails userDetails = User.builder()
        // .username("user")
        // .password(passwordEncoder.encode("password"))
        // .roles("USER")
        // .build();

        // return new InMemoryUserDetailsManager(userDetails);
        // }

        @Bean
        public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
                RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                                .clientId("hisami")
                                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                                .redirectUri("http://localhost:4200")
                                .postLogoutRedirectUri("http://localhost:4200")
                                .scope(OidcScopes.OPENID)
                                .scope(OidcScopes.PROFILE)
                                .clientSettings(ClientSettings.builder().requireProofKey(true).build()) // 🔥 Ativa PKCE
                                .build();

                return new InMemoryRegisteredClientRepository(oidcClient);
        }
}
