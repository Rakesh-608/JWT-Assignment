import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private apiUrl = 'http://localhost:8080/api/employees';

  constructor(private http: HttpClient) {}

  createEmployee(employee: Employee): Observable<Employee> {
    console.log('Sending employee:', JSON.stringify(employee));
    console.log()
    return this.http.post<Employee>(this.apiUrl, employee);
  }
}
