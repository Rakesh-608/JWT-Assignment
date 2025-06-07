import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthRequest, AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm!: FormGroup;
  message:string='';

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    console.log("Is logged in "+this.authService.isLoggedIn())
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const loginData: AuthRequest = this.loginForm.value;
      this.authService.login(loginData).subscribe({
        next: (response) => {
          console.log('Login successful', response);
          this.router.navigate(['/dashboard']).then(success => {
            if (!success) {
              console.error('Navigation to /dashboard failed');
              this.message = 'Login successful, but failed to redirect to dashboard';
            }
          });
        },
        error: (err) => {
          console.error('Login error:', err);
          this.message = err.message || 'Login failed';
        }
      });
    } else {
      this.message = 'Please fill out the form correctly';
    }
  }
}
