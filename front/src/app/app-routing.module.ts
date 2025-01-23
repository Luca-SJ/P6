import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './features/auth/components/register/register.component';
import { LoginComponent } from './features/auth/components/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';
import { MeComponent } from './components/me/me.component';
import { ArticlesComponent } from './articles/articles.component';
import { TopicComponent } from './topic/components/topics.component';
import { HeaderComponent } from './header/header.component';
import { ArticlesDetailsComponent } from './articles-details/articles-details.component';
import { UserResolver } from './resolver/user.resolver';
import { CreateArticlesComponent } from './create-articles/create-articles.component';

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
    canActivate: [AuthGuard],
    component: RegisterComponent
  },

  { 
    path: 'login',
    canActivate: [UnauthGuard],
    component: LoginComponent
  },

  { 
    path: 'me',
    canActivate: [AuthGuard],
    resolve: { user: UserResolver },
    component: MeComponent
  },

  { 
    path: 'articles',
    canActivate: [AuthGuard],
    component: ArticlesComponent
  },

  { 
    path: 'articles/:id',
    canActivate: [AuthGuard],
    resolve: { user: UserResolver },
    component: ArticlesDetailsComponent
  },

  { 
    path: 'topics',
    canActivate: [AuthGuard],
    component: TopicComponent
  },

  { 
    path: 'create-articles',
    canActivate: [AuthGuard],
    resolve: { user: UserResolver },
    component: CreateArticlesComponent
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
