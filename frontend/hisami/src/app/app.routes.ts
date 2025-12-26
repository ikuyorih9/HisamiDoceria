import { Routes } from '@angular/router';
import { LoginPage } from './pages/login/login.page';
import { SignupPage } from './pages/signup/signup.page';
import { HomePage } from './pages/home/home.page';
export const routes: Routes = [
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
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomePage, pathMatch: 'full' },
];
