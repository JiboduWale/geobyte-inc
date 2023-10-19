import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { LoginRequest } from '../interfaces/login-request';
import {tap} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  errorMessage: string = '';


  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
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

    // Calling user service's login method with the LoginRequest object
    this.userService.login(loginRequest).pipe(
      tap(response => {
        // Handle successful login
        this.router.navigate(['/location']).then(() => {
          // Navigation was successful
        }).catch(error => {
          // Handle the error here
          console.error('Navigation error:', error);
        });
      })
    ).subscribe({
      next: (response) => {
        // Handle a successful login
        console.log('Login successful');
        this.router.navigate(['/user-dashboard']).then(r => true);
      },
      error: (error) => {
        this.loading = false;
        if (error.status === 401) {
          this.errorMessage = 'Invalid email or password. Please try again.';
        } else if (error.status === 0) {
          this.errorMessage = 'Network error. Please check your internet connection.';
        } else {
          this.errorMessage = 'An unknown error occurred. Please try again later.';
        }
      }
    });
  }
}
