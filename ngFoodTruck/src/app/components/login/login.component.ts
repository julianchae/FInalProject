import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: User = new User();

  constructor(
    private auth: AuthService,
    private router: Router


  ) { }

  ngOnInit(): void {
  }

  login(user: User){
    console.log(user);
    this.auth.login(user.username, user.password).subscribe();
  }

}
