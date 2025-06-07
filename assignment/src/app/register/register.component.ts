// import { Component } from '@angular/core';
// import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
// import { Router } from '@angular/router';
// import { AuthService } from '../services/auth.service';

// @Component({
//   selector: 'app-register',
//   standalone: true,
//   imports: [ReactiveFormsModule],
//   templateUrl: './register.component.html',
//   styleUrl: './register.component.css'
// })
// export class RegisterComponent {
//   registerForm!: FormGroup;

//   constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {}

//   ngOnInit(): void {
//     this.registerForm = this.fb.group({
//       username: ['', Validators.required],
//       password: ['', Validators.required],
//     });
//   }

//   onSubmit(): void {
//     if (this.registerForm.valid) {
//       this.authService.register(this.registerForm.value).subscribe({
//         next: (response) => {
//           console.log('Registration successful', response);
//           setTimeout(() => {
//             this.router.navigate(['/login']);
//           }, 1000)
//         },
//         error: (error) => {
//           console.error('Registration failed', error);
//         }
//       });
//     }
//   }
// }


import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService, AuthRequest, ApiResponse } from '../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  message = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const registerData: AuthRequest = this.registerForm.value;
      this.authService.register(registerData).subscribe({
        next: (response: ApiResponse) => {
          console.log('Registration successful', response);
          this.message = response.message || 'User registered successfully';
          this.router.navigate(['/login']).then(success => {
            if (!success) {
              console.error('Navigation to /login failed');
              this.message = 'Registration successful, but failed to redirect to login';
            }
          });
        },
        error: (err) => {
          console.error('Registration error:', err);
          this.message = err.message || 'Registration failed';
        }
      });
    } else {
      this.message = 'Please fill out the form correctly';
    }
  }
}