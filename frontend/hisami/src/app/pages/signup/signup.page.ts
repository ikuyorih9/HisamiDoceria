import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.page.html',
  styleUrl: './signup.page.scss',
  imports: [ReactiveFormsModule],
})
export class SignupPage {
  profileForm = new FormGroup({
    name: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    cpf: new FormControl('', { nonNullable: true, validators: [Validators.required] }), // string
    username: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    password: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    salary: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(0)],
    }),
    role: new FormControl('ATENDENTE', {
      nonNullable: true,
      validators: [Validators.required, Validators.pattern(/^(GERENTE|ATENDENTE|COZINHEIRO)$/)],
    }),
  });

  constructor(private apiService: ApiService) {}

  // mantém só dígitos enquanto digita
  public onCpfInput(event: Event) {
    const input = event.target as HTMLInputElement;
    const digits = input.value.replace(/\D/g, '').slice(0, 11);
    this.profileForm.controls.cpf.setValue(digits, { emitEvent: false });
    input.value = digits; // reflete no input
  }

  public submit() {
    if (this.profileForm.invalid) {
      this.profileForm.markAllAsTouched(); // força aparecer mensagens de erro
      alert('Preencha os campos vazios.');
      return;
    }

    const apiUrl = 'http://localhost:8080/auth/signup';
    const usuario = {
      name: this.profileForm.value.name,
      cpf: this.profileForm.value.cpf,
      username: this.profileForm.value.username,
      email: this.profileForm.value.name,
      password: this.profileForm.value.password,
      salary: this.profileForm.value.salary,
      role: this.profileForm.value.role,
    };
    this.apiService
      .post<any>(apiUrl, usuario)
      .then((response: string) => {
        alert(response);
      })
      .catch((error: Error) => {
        alert(error.message);
      });
  }
}
