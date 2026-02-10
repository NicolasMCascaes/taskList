import { Component } from '@angular/core';
import { TaskCard } from "../task-card/task-card";

@Component({
  selector: 'app-dashboard',
  imports: [TaskCard],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard {

}
