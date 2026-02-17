import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth-service';

@Component({
  selector: 'app-oauth2-redirect',
  imports: [],
  templateUrl: './oauth2-redirect.html',
  styleUrl: './oauth2-redirect.css'
})
export class Oauth2Redirect implements OnInit {
  constructor(private router: Router, private auth: AuthService) {}

  ngOnInit(): void {
    const params = new URLSearchParams(window.location.search);
    const token = params.get('token');

    if (token) {
      this.auth.setToken(token);
      this.router.navigate(['/dashboard']);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
