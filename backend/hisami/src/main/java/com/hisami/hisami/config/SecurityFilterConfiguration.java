package com.hisami.hisami.config;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterConfiguration {
        @Value("${app.oauth2.redirect-uri}")
        private String loginRedirectUri;

        @Value("${app.oauth2.post-logout-redirect-uri}")
        private String postLogoutRedirectUri;

        @Bean
        @Order(2)
        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
                        throws Exception {
                http
                                .httpBasic(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable()) // Desativa CSRF para facilitar testes com POST
                                .cors(Customizer.withDefaults())
                                .authorizeHttpRequests((authorize) -> authorize
                                                .requestMatchers("/login", "/styles/**", "/images/**", "/fonts/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                                .requestMatchers("/auth/**").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login") // sua página
                                                .loginProcessingUrl("/login")
                                                // .defaultSuccessUrl("/", true)
                                                .defaultSuccessUrl(this.loginRedirectUri, true)
                                                .failureUrl("/login?error=true")
                                                .permitAll())
                                .httpBasic(httpBasic -> httpBasic.disable()) // <-- importante
                                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

                return http.build();
        }

        @Bean
        public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
                RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                                .clientId("hisami")
                                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                                .redirectUri(this.loginRedirectUri)
                                .postLogoutRedirectUri(this.postLogoutRedirectUri)
                                .scope(OidcScopes.OPENID)
                                .scope(OidcScopes.PROFILE)
                                .clientSettings(ClientSettings.builder().requireProofKey(true).build()) // 🔥 Ativa PKCE
                                .build();

                return new InMemoryRegisteredClientRepository(oidcClient);
        }
}
