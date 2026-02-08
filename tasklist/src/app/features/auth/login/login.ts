import { AuthService } from './../../../core/services/auth-service';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from "@angular/router";
import iziToast from 'izitoast';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  private authService: AuthService
  private router: Router

  constructor(){
    this.authService = inject(AuthService)
    this.router = inject(Router)
  }
 email: string = ''
 senha: string = ''

async handleLogin(event: Event){
  const loginData = {
    email: this.email,
    password: this.senha
  }
  event.preventDefault()
  this.authService.loginUser(loginData).subscribe({
    next:() => {
      iziToast.success({
        title:"Sucesso!",
        message:"Login efetuado com sucesso!",
        position:"topCenter"
      })
      setTimeout(()=>{
        this.router.navigate(['/dashboard'])
      }, 1000)
    },
    error: (err) => iziToast.error({
      title:"Erro ao realizar login, tente novamente",
      message: err
    })
  })
}
}
