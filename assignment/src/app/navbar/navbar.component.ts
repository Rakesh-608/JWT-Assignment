import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { CommonModule, NgIf } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink, NgIf],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit, OnDestroy {
  // username: string | null = null;
  // private authSubscription: Subscription | null = null;

  // constructor(
  //   public authService: AuthService,
  //   private router: Router,
  //   private jwtHelper: JwtHelperService,
  //   private cdr: ChangeDetectorRef
  // ) {}

  // ngOnInit(): void {
  //   this.setUsername();
  //   this.authSubscription = this.authService.authState$.subscribe(isLoggedIn => {
  //     if (isLoggedIn) {
  //       this.setUsername();
  //     } else {
  //       this.username = null;
  //     }
  //     this.cdr.detectChanges();
  //   });
  // }

  // ngOnDestroy(): void {
  //   if (this.authSubscription) {
  //     this.authSubscription.unsubscribe();
  //   }
  // }

  // private setUsername(): void {
  //   const token = this.authService.getToken();
  //   if (token && !this.jwtHelper.isTokenExpired(token)) {
  //     const decodedToken = this.jwtHelper.decodeToken(token);
  //     this.username = decodedToken.sub || null;
  //     console.log("username"+this.username)
  //   } else {
  //     this.username = null;
  //   }
  // }

  // logout(): void {
  //   this.authService.logout();
  //   this.router.navigate(['/login']).then(() => {
  //     this.cdr.detectChanges();
  //   });
  // }


  isLoggedIn: boolean = false;
  private authSubscription: Subscription | null = null;

  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = !!this.authService.getToken();
    this.authSubscription = this.authService.authState$.subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
    });
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}