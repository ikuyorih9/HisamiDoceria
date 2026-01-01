import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-login',
  templateUrl: '/login.page.html',
  styleUrl: '/login.page.scss',
  imports: [],
})
export class LoginPage implements OnInit {
  constructor(
    private authService: AuthService,
    private router: Router,
    private oauthService: OAuthService,
    private auth: AuthService,
  ) {}

  async ngOnInit() {
    await this.oauthService.loadDiscoveryDocumentAndTryLogin();

    if (this.auth.isAuthenticated()) {
      const target = this.auth.getRedirectUrl() ?? '/home';
      await this.router.navigateByUrl(target);
      return;
    }

    // se falhou, tenta logar de novo ou vai para home
    this.oauthService.initLoginFlow();
  }

  login() {
    console.log('[LOGIN PAGE]: login');
    this.authService.login();
  }
  signup() {
    this.router.navigate(['signup']);
  }
}
