import { AuthConfig } from 'angular-oauth2-oidc';
export const AUTH_CONFIG: AuthConfig = {
    issuer: 'http://localhost:8080', // Servidor de autenticação
    redirectUri: 'http://localhost:4200', // URL do frontend Angular
    clientId: 'hisami', // ID do cliente registrado no servidor OAuth2
    responseType: 'code', // Authorization Code Flow
    scope: 'openid profile', // Escopos necessários
    showDebugInformation: true,
    strictDiscoveryDocumentValidation: false,
    tokenEndpoint: 'http://localhost:8080/oauth2/token',
    loginUrl: 'http://localhost:8080/oauth2/authorize',
    requireHttps: false,
    logoutUrl: 'http://localhost:8080/logout',
    postLogoutRedirectUri: 'http://localhost:4200'
  };