import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  title:string= '';
  isLoading: boolean = true; // Track loading state
  error: string = '';

  constructor(private authService: AuthService){}

  // ngOnInit(): void {
  //   this.authService.getProtectedResource().subscribe({
  //     next: (response) => {
  //       this.title = response;
  //       console.log('Protected resource:', response); // Debug log
  //     },
  //     error: (err) => {
  //       console.error('Error fetching protected resource:', err);
  //       this.title = 'Error loading resource';
  //     }
  //   });
  // }

  ngOnInit(): void {
    const token = this.authService.getToken();
    console.log('Dashboard: JWT token=', token ? 'present' : 'none'); // Debug token
    if (!token) {
      this.error = 'Please log in to access the dashboard';
      this.isLoading = false;
      return;
    }

    this.authService.getProtectedResource().subscribe({
      next: (response) => {
        this.title = response;
        this.isLoading = false;
        console.log('Protected resource:', response);
      },
      error: (err) => {
        console.error('Error fetching protected resource:', err);
        this.error = err.status === 401 ? 'Unauthorized: Invalid or missing token' : 'Failed to load resource';
        this.isLoading = false;
      }
    });
  }

}
