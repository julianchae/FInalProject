import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }




  logout(){
      console.log('logged out')
      this.auth.logout();
      this.router.navigateByUrl('/home');
  }



  ngOnInit(): void {
  }

}
