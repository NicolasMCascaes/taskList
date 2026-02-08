import { Routes } from '@angular/router';
import { Login } from './features/auth/login/login';
import { Layout } from './layouts/layout/layout';
import { Dashboard } from './features/dashboard/dashboard';
import { Register } from './features/auth/register/register';

export const routes: Routes = [
  {
    path:"",
    redirectTo:'login',
    pathMatch:'full'
  },
  {
    path:'login',
    component:Login
  },
  {
    path:'register',
    component:Register
  },
  {
    path:'',
    component: Layout,
    children:[
      {
        path:'dashboard',
        component: Dashboard
      }
    ]
  }
];
