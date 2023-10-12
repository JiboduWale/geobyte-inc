import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { LoginRequest } from '../interfaces/login-request'; // Import the LoginRequest model

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  // Convenience getter for easy access to form fields
  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onSubmit() {
    this.submitted = true;

    // Stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;

    // Create a LoginRequest object
    const loginRequest: LoginRequest = {
      email: this.email!.value,
      password: this.password!.value
    };

    // Call your user service's login method with the LoginRequest object
    this.userService.login(loginRequest)
      .subscribe(
        response => {
          // Handle successful login
          this.router.navigate(['/dashboard']); // Navigate to the dashboard or desired page
        },
        error => {
          // Handle login error, show error message
          this.loading = false;
          // You can display an error message to the user here
        }
      );
  }
}
