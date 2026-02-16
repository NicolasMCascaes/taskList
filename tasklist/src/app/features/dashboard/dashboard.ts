import { Component } from '@angular/core';
import { TaskCard } from "../task-card/task-card";
import { TaskChartComponent } from "../task-chart/task-chart";

@Component({
  selector: 'app-dashboard',
  imports: [TaskCard, TaskChartComponent],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

}
