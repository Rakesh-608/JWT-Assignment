import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmployeeformComponent } from './employeeform/employeeform.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthGaurdService } from './services/auth-gaurd.service';
import { AuthService } from './services/auth.service';

export const routes: Routes = [
    { path:'dashboard', component: DashboardComponent, canActivate: [AuthGaurdService]},
    { path:'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent},
    { path: 'modify', component: EmployeeformComponent},
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
];
