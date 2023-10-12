import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
})
export class RegistrationComponent {
  user: any = {};

  constructor(private authService: AuthService) {}

  register() {
    this.authService.register(this.user).subscribe((response) => {
      // Handle registration success or errors here
    });
  }
}

