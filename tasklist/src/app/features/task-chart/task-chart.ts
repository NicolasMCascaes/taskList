import { Component, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';


Chart.register(...registerables);

@Component({
  selector: 'app-task-chart',
  templateUrl: './task-chart.html',
  styleUrls: ['./task-chart.css']
})
export class TaskChartComponent implements AfterViewInit {

  @ViewChild('chartCanvas') chartCanvas!: ElementRef;

  chart!: Chart;

  ngAfterViewInit(): void {
    const completed = 8;
    const pending = 5;
    const cancelled = 3;

    this.chart = new Chart(this.chartCanvas.nativeElement, {
      type: 'doughnut',
      data: {
        labels: ['Concluídas', 'Pendentes', 'Em andamento'],
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
  }
}
