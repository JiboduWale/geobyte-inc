import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { NavigationComponent } from './navigation/navigation.component';
import { DeliveryComponent } from './delivery/delivery.component';
import { LocationManagementComponent } from './location-management/location-management.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { FeedbackComponent } from './feedback/feedback.component';
import {UserService} from "./services/user.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {LocationService} from "./services/location.service";
import {DeliveryService} from "./services/delivery.service";

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    NavigationComponent,
    DeliveryComponent,
    LocationManagementComponent,
    UserDashboardComponent,
    ErrorPageComponent,
    FeedbackComponent
  ],
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule
    ],
  providers: [UserService,
    LocationService,
    DeliveryService,
    HttpClient
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
