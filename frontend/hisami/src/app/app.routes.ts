import { Routes } from '@angular/router';
import { LoginPage } from './pages/login/login.page';
import { SignupPage } from './pages/signup/signup.page';

export const routes: Routes = [
  {
    path: 'home',
    redirectTo: '/',
  },
  {
    path: 'login',
    component: LoginPage,
    title: 'Login',
  },
  {
    path: 'signup',
    component: SignupPage,
    title: 'Sign up',
  },
];
