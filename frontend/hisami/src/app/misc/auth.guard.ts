import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    console.log('[AUTH GUARD] canActivate for', state.url);

    if (this.auth.isAuthenticated()) {
      return true;
    }

    // salva a URL que o usuário queria
    this.auth.setRedirectUrl(state.url);

    // inicia login (vai sair da SPA)
    this.auth.login();

    return false;
  }
}
