import { MenuComponent } from './components/menu/menu.component';
import { CrudFoodTruckComponent } from './components/crud-food-truck/crud-food-truck.component';
import { SingleTruckComponent } from './components/single-truck/single-truck.component';
import { LogoutComponent } from './components/logout/logout.component';
import { LoginComponent } from './components/login/login.component';
import { Login } from './models/login';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';



const routes: Routes = [
  {path:'register', component: RegisterComponent},
  {path:'login', component: LoginComponent},
  {path:'logout', component: LogoutComponent},
  {path:'truck/:id', component: SingleTruckComponent},
  {path:'home', component: HomeComponent},
  {path:'profile', component: ProfileComponent},
  {path:'updateFoodTruck', component: CrudFoodTruckComponent},
  {path:'updateMenuItem', component: MenuComponent},
  {path:'**', component: HomeComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes,  {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
