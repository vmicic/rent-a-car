import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(
    private router: Router,
    private jwtHelper: JwtHelperService
  ) { }

  login() {
    this.loggedIn.next(true);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  isTokenPresent() {
    return localStorage.getItem('token');
  }

  isAuthenticated() {
    if (this.isTokenPresent()) {
      const token = localStorage.getItem('token');
      return !this.jwtHelper.isTokenExpired(token);
    }
    return false;
  }

  getUserRole() {
    const token = localStorage.getItem('token');
    const payload = this.jwtHelper.decodeToken(token);
    return payload.role;
  }

  get isLoggedIn() {
    if (this.isAuthenticated()) {
      this.loggedIn.next(true);
      return this.loggedIn;
    } else {
      this.loggedIn.next(false);
      return this.loggedIn;
    }
  }
}
