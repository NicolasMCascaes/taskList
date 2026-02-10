import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-task-card',
  imports: [NgClass],
  templateUrl: './task-card.html',
  styleUrl: './task-card.css'
})
export class TaskCard {
  @Input() taskId!: string;
  @Input() status!: string;
  @Input() title!: string;
  @Input() description!: string;
  @Output() complete = new EventEmitter<string>();
  @Output() cancel = new EventEmitter<string>();

  getStatusColor(): string{
    if (this.status == 'COMPLETED') {
      return 'bg-success'
    }else if (this.status === 'CANCELLED') {
      return 'bg-danger'
    }
    return 'bg-warning'
  }
  getStatusIcon(): string{
    if (this.status == 'COMPLETED') {
      return 'bi bi-check-lg fs-1'
    }else if (this.status === 'CANCELLED') {
      return 'bi bi-x-lg fs-1'
    }
    return 'bi bi-clock-fill text-white fs-1'
  }

  canChangeStatus(): boolean {
    return this.status === 'PENDING';
  }

  onComplete(): void {
    if (!this.canChangeStatus()) return;
    this.complete.emit(this.taskId);
  }

  onCancel(): void {
    if (!this.canChangeStatus()) return;
    this.cancel.emit(this.taskId);
  }
}
