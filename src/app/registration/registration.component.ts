import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { RegistrationRequest } from '../interfaces/registration-request';
import {tap} from "rxjs";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;
  loading = false;
  submitted = false;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get name() {
    return this.registrationForm.get('name');
  }
  get email() {
    return this.registrationForm.get('email');
  }

  get password() {
    return this.registrationForm.get('password');
  }

  get f() { return this.registrationForm.controls; }

  onSubmit() {
    this.submitted = true;
    this.errorMessage = '';

    if (this.registrationForm.invalid) {
      return;
    }

    this.loading = true;

    const registrationRequest: RegistrationRequest = {
      name: this.name!.value,
      email: this.email!.value,
      password: this.password!.value
    };

    // Calling user service's registration method with the LoginRequest object
    this.userService.register(registrationRequest).pipe(
      tap(response => {
        // Handle successful login
        this.router.navigate(['/dashboard']).then(() => {
          // Navigation was successful
        }).catch(error => {
          // Handle the error here
          console.error('Navigation error:', error);
        });
      })
    ).subscribe(
      error => {
        this.loading = false;
        this.errorMessage = 'Registration failed. Please try again.';
      }
    );
  }
}
