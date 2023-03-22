import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  role:string | null ="user" ;

  ngOnInit(): void {
    //throw new Error('Method not implemented.');
   // localStorage.setItem('auth', 'false');

    console.log("Role check!");
    
    this.role = sessionStorage.getItem("role");
  }

  constructor(public router: Router) { }
  title = 'wallet-frontend';

  logout() {
   // localStorage.setItem('auth', 'false');

    console.log("User Logout!");
    sessionStorage.clear();    
    this.role =sessionStorage.getItem("role");
  }

  checkRole():string{
    let role = sessionStorage.getItem("role");

    switch(role){
      case "user":return "user"
      case "admin":return "admin"
      default:return "public"
    }
  }
}
