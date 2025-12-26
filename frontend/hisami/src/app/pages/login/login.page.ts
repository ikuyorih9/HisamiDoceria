import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: '/login.page.html',
  styleUrl: '/login.page.scss',
  imports: [],
})
export class LoginPage {
  constructor(private authService: AuthService, private router: Router) {}

  login() {
    console.log('[LOGIN PAGE]: login');
    this.authService.login();
  }
  signup() {
    this.router.navigate(['signup']);
  }
}
