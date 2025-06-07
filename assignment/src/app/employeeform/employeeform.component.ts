import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-employeeform',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './employeeform.component.html',
  styleUrl: './employeeform.component.css'
})
export class EmployeeformComponent {
  employeeForm: FormGroup;
  error: string = '';

  constructor(private fb: FormBuilder, private employeeService: EmployeeService) {
    this.employeeForm = this.fb.group({
      filenumber: ['', [Validators.required, Validators.min(1)]],
      firstName: ['', [Validators.required, Validators.minLength(1)]],
      lastName: ['', [Validators.required, Validators.minLength(1)]],
      gender: ['', [Validators.required, Validators.minLength(1)]],
      dateOfBirth: ['', [Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/)]],
      address1: ['', [Validators.required, Validators.minLength(1)]],
      address2: [''],
      phoneNumber1: ['', [Validators.required, Validators.minLength(1)]],
      phoneNumber2: ['']
    });
  }

  onSubmit() {
    if (this.employeeForm.valid) {
      this.employeeService.createEmployee(this.employeeForm.value).subscribe({
        next: (response) => console.log('Employee created:', response),
        error: (err) => {
          console.error('Error:', err);
          this.error = err.error?.error || 'Failed to create employee';
        }
      });
    }
  }
}
