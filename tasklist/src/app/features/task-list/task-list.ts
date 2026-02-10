import { ChangeDetectorRef, Component , inject, OnInit} from '@angular/core';

import { TaskCard } from "../task-card/task-card";
import { ActivatedRoute } from '@angular/router';
import { TaskService } from '../../core/services/task-service';
import { AuthService } from '../../core/services/auth-service';

@Component({
  selector: 'app-task-list',
  imports: [TaskCard],
  templateUrl: './task-list.html',
  styleUrl: './task-list.css'
})
export class TaskList implements OnInit{
 status: string = '';
 tasks: any[] = []
 userId: string = ''
 isLoading = false;
private taskSerive: TaskService;
private cdr = inject(ChangeDetectorRef)
private authService: AuthService
  constructor(private route: ActivatedRoute) {this.taskSerive = inject(TaskService), this.authService = inject(AuthService)}

   ngOnInit() {
     this.route.paramMap.subscribe(params => {
      this.status = params.get('status') || '';
      this.loadTasks()
    });

   }
   loadTasks(){
   const userId = this.authService.getUserId();
    if (!userId) {
    console.error('Usuário não autenticado — ID ausente');
    return;
  }
    this.tasks = [];
    this.taskSerive.listAllTasksByUserIdAndStatus(userId, this.status.toUpperCase()).subscribe({

      next:(response) =>{
        this.tasks = response
         this.cdr.detectChanges()
        console.log(response)
      },
      error:(err)=>console.error(err)
    })
   }
  completeTask(taskId: string): void {
    if (!taskId) return;
    this.taskSerive.completeTask(taskId).subscribe({
      next: () => this.loadTasks(),
      error: (err) => console.error(err)
    });
  }

  cancelTask(taskId: string): void {
    if (!taskId) return;
    this.taskSerive.cancelTask(taskId).subscribe({
      next: () => this.loadTasks(),
      error: (err) => console.error(err)
    });
  }
}
