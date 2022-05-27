import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    if(localStorage.getItem('credentials')) {
      this.loggedin = true;
    } else {
      this.loggedin = false;
    }
  }

  loggedin: boolean = false;
}
