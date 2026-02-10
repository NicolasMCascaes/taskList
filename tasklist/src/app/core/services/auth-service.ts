import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
interface JwtPayload {
  sub?: string;       // campo padrão em JWT (id do usuário)
  userId?: string;    // campo alternativo, caso seu backend use outro nome
  id?: string;        // outro nome possível
  exp?: number;       // tempo de expiração
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
     private apiUrl = 'http://localhost:8080/api/v1/auth';
     private tokenSubject = new BehaviorSubject<string>('');
     public token$ = this.tokenSubject.asObservable();

  constructor(private http: HttpClient) {
    const savedToken = localStorage.getItem('token');
    if (savedToken) {
      this.tokenSubject.next(savedToken);
    }
  }

  registerUser(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  loginUser(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, data);
  }
  setToken(token: string ){
    localStorage.setItem('token', token)
    this.tokenSubject.next(token)
  }
  getToken() :string{
    return this.tokenSubject.value;
  }
  removeToken():void{
    localStorage.removeItem('token')
    this.tokenSubject.next('')
  }
  isAuthenticated(): boolean {
    return this.tokenSubject.value !== '';
  }
   getUserId(): string | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const payload = jwtDecode<JwtPayload>(token);
      return payload.id ?? null;
    } catch (error) {
      console.error('Erro ao decodificar token:', error);
      return null;
    }
  }
}
