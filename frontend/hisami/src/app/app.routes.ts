import { Routes } from '@angular/router';
import { LoginPage } from './pages/login/login.page';
import { SignupPage } from './pages/signup/signup.page';
import { HomePage } from './pages/home/home.page';
import { SecureAreaPage } from './pages/secure-area/secure-area.page';
import { AuthGuard } from './misc/auth.guard';
import { MenuPage } from './pages/menu/menu.page';

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
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomePage,
    title: 'Home',
  },
  {
    path: 'menu',
    component: MenuPage,
    title: 'Cardápio',
  },
  {
    path: 'area-restrita',
    component: SecureAreaPage,
    title: 'Área Restrita',
    canActivate: [AuthGuard],
  },
];
