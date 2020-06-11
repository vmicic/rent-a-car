import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/shared/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {

    const role = this.authService.getUserRole();

    if(role === "ROLE_USER") {
      this.router.navigate(['home']);
    } else if(role === "ROLE_AGENT"){
      this.router.navigate(['agent']);
    } else if(role === "ROLE_ADMINISTRATOR") {
      this.router.navigate(['admin'])
    }
  }

}
