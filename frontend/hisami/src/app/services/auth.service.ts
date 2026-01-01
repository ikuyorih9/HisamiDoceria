import { Injectable } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { AUTH_CONFIG } from '../constants/AuthConfig.constants';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  public redirectUrl: string | null = null;
  private readonly REDIRECT_KEY = 'auth_redirect_url';

  constructor(
    private oauthService: OAuthService,
    private router: Router,
  ) {
    this.oauthService.configure(AUTH_CONFIG);
    // this.oauthService.loadDiscoveryDocumentAndTryLogin();
    // this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
    //   if (this.isAuthenticated()) {
    //     console.log('✅ Token obtido com sucesso:', this.oauthService.getAccessToken());
    //     this.redirectAfterLogin(); // Redireciona após login bem-sucedido
    //   } else {
    //     console.log('❌ Falha ao obter o token.');
    //   }
    // });
  }

  // # ----------------------- #
  // # --- PUBLIC METHODS --- #
  // # ----------------------- #

  public login() {
    this.oauthService.initLoginFlow();
  }

  public logout() {
    this.oauthService.logOut();
  }

  public isAuthenticated(): boolean {
    console.log(`Token expires at ${this.findWhenExpires().toISOString()}`);
    const token = this.oauthService.getAccessToken();
    const isValid = !this.isTokenExpired(token);
    console.log('🔍 Verificando token:', isValid ? '✅ Válido' : '❌ Inválido');
    return isValid;
  }

  public setRedirectUrl(url: string) {
    console.log('[AuthService] setRedirectUrl called with:', url);
    try {
      this.redirectUrl = url;
      sessionStorage.setItem(this.REDIRECT_KEY, url);
      console.log('[AuthService] saved:', sessionStorage.getItem(this.REDIRECT_KEY));
    } catch (e) {
      console.error('[AuthService] sessionStorage failed:', e);
    }
  }

  public getRedirectUrl(): string | null {
    const url = sessionStorage.getItem(this.REDIRECT_KEY);
    sessionStorage.removeItem(this.REDIRECT_KEY);
    return url;
  }

  // # ----------------------- #
  // # --- PRIVATE METHODS --- #
  // # ----------------------- #

  private findWhenExpires(): Date {
    const expiresAt = this.oauthService.getAccessTokenExpiration(); // number
    return new Date(expiresAt); // converte para Date
  }

  private isTokenExpired(token: string): boolean {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const now = Math.floor(Date.now() / 1000);
      return payload.exp < now;
    } catch (e) {
      return true;
    }
  }

  public redirectAfterLogin() {
    if (this.redirectUrl) {
      this.router.navigateByUrl(this.redirectUrl);
      this.redirectUrl = null; // Limpa a URL armazenada
    } else {
      this.router.navigate(['/home']); // Redireciona para a home se não houver URL armazenada
    }
  }
}
