import { AuthService } from './../../../core/services/auth-service';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from "@angular/router";
import iziToast from 'izitoast';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
  standalone:true
})
export class Register {
  private authService: AuthService;
  private router: Router
  constructor() {
    this.authService = inject(AuthService)
    this.router = inject(Router)
  }
   username: string = '';
    email: string = '';
  senha: string = '';
  async handleRegister(event:Event){
    event.preventDefault();
    const registerData = {
      email: this.email,
      password: this.senha,
      username: this.username
    }
      this.authService.registerUser(registerData).subscribe({
         next: () => {
        iziToast.success({
          title: 'Sucesso!',
          message: 'Conta criada com sucesso!',
          position: 'topCenter',
          timeout: 3000
        });

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1000);
      },
        error: (err) => console.error(err)
      })
    }
  }
