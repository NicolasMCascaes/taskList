import { Routes } from '@angular/router';
import { Login } from './features/auth/login/login';
import { Layout } from './layouts/layout/layout';
import { Dashboard } from './features/dashboard/dashboard';
import { Register } from './features/auth/register/register';
import { TaskList } from './features/task-list/task-list';
import { Oauth2Redirect } from './features/auth/oauth2-redirect/oauth2-redirect';

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
    path:'oauth2/redirect',
    component:Oauth2Redirect
  },
  {
    path:'',
    component: Layout,
    children:[
      {
        path:'dashboard',
        component: Dashboard
      },
      {
      path:'tasks/:status',
      component: TaskList
      }
    ]
  }
];
