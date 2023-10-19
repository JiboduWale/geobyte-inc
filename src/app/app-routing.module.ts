import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import {LocationManagementComponent} from "./location-management/location-management.component";
import {DeliveryComponent} from "./delivery/delivery.component";
import {UserDashboardComponent} from "./user-dashboard/user-dashboard.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'location', component: LocationManagementComponent },
  { path: 'delivery', component: DeliveryComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: '', redirectTo: '/register', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
