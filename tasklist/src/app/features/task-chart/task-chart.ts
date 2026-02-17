import { Component, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { TaskService } from '../../core/services/task-service';
import { AuthService } from '../../core/services/auth-service';

Chart.register(...registerables);

@Component({
  selector: 'app-task-chart',
  templateUrl: './task-chart.html',
  styleUrls: ['./task-chart.css']
})
export class TaskChartComponent implements AfterViewInit {

  @ViewChild('chartCanvas') chartCanvas!: ElementRef;

  chart!: Chart;

  constructor(private taskService: TaskService, private authService: AuthService) {}

  ngAfterViewInit(): void {
    const userId = this.authService.getUserId();
    if (!userId) {
      console.error('Usuário não autenticado - ID ausente');
      return;
    }

    this.taskService.countTaskByStatus(userId).subscribe({
      next: (response) => {
        const completed = response['COMPLETED'] ?? 0;
        const pending = response['PENDING'] ?? 0;
        const cancelled = response['CANCELLED'] ?? 0;

        this.chart = new Chart(this.chartCanvas.nativeElement, {
          type: 'doughnut',
          data: {
            labels: ['Concluídas', 'Pendentes', 'Canceladas'],
            datasets: [{
              data: [completed, pending, cancelled],
              backgroundColor: [
                '#4CAF50',
                '#FFC107',
                '#f32121'
              ],
            }]
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'bottom',
              },
              title: {
                display: true,
                text: 'Visão Geral das Tarefas'
              }
            }
          }
        });
      },
      error: (err) => console.error(err)
    });
  }
}
