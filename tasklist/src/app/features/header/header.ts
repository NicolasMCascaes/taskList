import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth-service';
import { TaskService } from '../../core/services/task-service';
import iziToast from 'izitoast';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  imports: [FormsModule],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  private authService: AuthService
  private router: Router
  private taskService: TaskService
  taskTitle: string = ''
  taskDescription: string = ''
  userId: string = ''
  constructor(){this.authService = inject(AuthService), this.router = inject(Router), this.taskService = inject(TaskService)}
  handleLogout(){
    this.authService.removeToken();
    this.router.navigate(['/login'])
  }
  async handleSubmitTask(event: Event){
    const userId = this.authService.getUserId();
    console.log(userId)
    if (!userId) {
      iziToast.error({ title: 'Erro', message: 'Usuário não autenticado.' });
      return;
    }
    const taskData = {
      taskTitle: this.taskTitle,
      taskDescription: this.taskDescription,
      userId
    }
    this.taskService.saveTask(taskData).subscribe({
      next: ()=>{
        iziToast.success({
          title: 'Sucesso!',
          message:'Tarefa registrada com sucesso!'
        });
      },
      error:(err)=>iziToast.error({
        title:'Erro',
        message:'Erro ao registrar tarefa, tente novamente'
      })
    })

  }
}
