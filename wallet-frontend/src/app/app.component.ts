import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  ngOnInit(): void {
    //throw new Error('Method not implemented.');
    localStorage.setItem('auth', 'false');
  }

  constructor(public router: Router) { }
  title = 'wallet-frontend';

  logout() {
    localStorage.setItem('auth', 'false');
  }
}
