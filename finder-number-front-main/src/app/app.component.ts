import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { ApiService } from './services/api.service';
import { ErrorResponse } from './interfaces/errorResponse';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ReactiveFormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  private fb = inject(FormBuilder);
  private readonly api = inject(ApiService);

  public result = signal<number | null>(null);
  public errorResponse = signal<String | null>(null);
  public loading = false;

  public formData: FormGroup;
  constructor() {

    this.formData = this.fb.group({
      valueX: ['', [Validators.required, Validators.min(2), Validators.max(Math.pow(10, 9))]], //X, 2 <= X <=10^9
      valueY: ['', [Validators.required, Validators.min(0)]], //Y, 0 <= Y < X
      valueN: ['', [Validators.required, Validators.max(Math.pow(10, 9))]] // N, y <= N <= 10^9
    });
    // Escuchar los cambios en el campo valueX
    const valueXControl = this.formData?.get('valueX');
    const valueYControl = this.formData?.get('valueY');
    if (valueXControl) {
      valueXControl.valueChanges.subscribe(value => {
        this.formData.controls['valueY'].setValidators([Validators.required,Validators.min(0), Validators.max(value - 1)]);
        this.formData.controls['valueY'].updateValueAndValidity;
      });
    }

    if (valueYControl) {
      valueYControl.valueChanges.subscribe(value => {
        this.formData.controls['valueN'].addValidators([Validators.required,Validators.min(value), Validators.max(Math.pow(10, 9))]);
        this.formData.controls['valueN'].updateValueAndValidity;
      });
    }

  }


  public isNotValidField(field: string) {
    return this.formData.controls[field].errors
      && this.formData.controls[field].touched;
  }

  public getFieldError(field: string) {
    if (!this.formData.controls[field]) return null;
    const errors = this.formData.controls[field].errors || {};

    for (const key of Object.keys(errors)) {
      switch (key) {
        case "required":
          return 'Este campo es requerido';
        case "min":
          return `El valor mínimo es ${errors['min'].min}`
        case 'max':
          return `El valor máximo es ${errors['max'].max}`
      }
    }
    return null;
  }



  public handleSubmit(method: 'GET'|'POST') {
    Object.keys(this.formData.controls).forEach(form => this.formData.get(form)?.markAsDirty());
    if (this.formData.valid) {
      const { valueX, valueY, valueN } = this.formData.value;
      
      const fn = (method === 'GET')
        ? this.api.findMaxKGet(valueX, valueY, valueN)
        : this.api.findMaxKPost({ x: valueX, y: valueY, n: valueN });

      this.errorResponse.set(null);
      this.result.set(null);
      this.loading = true;
      fn.subscribe({
        next: (resp) => {
          this.result.set(resp.result);
          this.loading = false;
        },
        error: (err) => {
          this.errorResponse.set(err.error.detail);
          this.loading = false;
        }
      })
    }
  }


}
