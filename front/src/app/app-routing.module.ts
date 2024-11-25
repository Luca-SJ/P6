import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './features/auth/components/register/register.component';
import { LoginComponent } from './features/auth/components/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';
import { MeComponent } from './components/me/me.component';
import { NewsComponent } from './news/news.component';
import { ThemesComponent } from './themes/components/themes.component';
import { HeaderComponent } from './header/header.component';
import { CreateNewsComponent } from './create-news/create-news.component';
import { NewsDetailsComponent } from './news-details/news-details.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {
    path: 'rentals',
    canActivate: [AuthGuard],
    loadChildren: () => import('./features/rentals/rentals.module').then(m => m.RentalsModule)
  },
  { 
    path: '',
    canActivate: [UnauthGuard],
    component: HomeComponent
  },

  { 
    path: 'register',
    canActivate: [UnauthGuard],
    component: RegisterComponent
  },

  { 
    path: 'login',
    canActivate: [UnauthGuard],
    component: LoginComponent
  },

  { 
    path: 'me',
    component: MeComponent
  },

  { 
    path: 'news',
    component: NewsComponent
  },

  { 
    path: 'news/:id',
    component: NewsDetailsComponent
  },

  { 
    path: 'themes',
    component: ThemesComponent
  },

  { 
    path: 'create-news',
    component: CreateNewsComponent
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
