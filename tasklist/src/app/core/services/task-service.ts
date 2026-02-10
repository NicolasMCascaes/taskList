import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = 'http://localhost:8080/api/v1/tasks'

  constructor(private http:HttpClient){}

  listAllTasksByUserIdAndStatus(userId: string, status: string): Observable<any>{
    return this.http.get(`${this.apiUrl}/listAllTasksByUserIdAndStatus?status=${status}&userId=${userId}`)
  }
  saveTask(data:any): Observable<any>{
    return this.http.post(`${this.apiUrl}/save`, data)
  }
  cancelTask(taskId: string): Observable<any>{
     return this.http.patch(`${this.apiUrl}/cancel/${taskId}`, {})
  }
   completeTask(taskId: string): Observable<any>{
     return this.http.patch(`${this.apiUrl}/complete/${taskId}`, {})
  }
}
