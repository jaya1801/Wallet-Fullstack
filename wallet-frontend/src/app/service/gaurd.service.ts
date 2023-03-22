import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class GaurdService implements CanActivate{

  constructor(private router:Router) { }
  canActivate(): boolean  {
    
    if(sessionStorage.getItem("jwt") != null){

      return   true;
    }
    else
    {
        Swal.fire({
          title: 'Not Authorised!',
          text: "Please Login to access!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Login'
        }).then((result) => {
          if (result.isConfirmed) {
            this.router.navigateByUrl("login");
          }
          else {
            Swal.fire('Not Logged in!!! \n Redirected to Home Page.....', '', 'info')
            this.router.navigateByUrl("home");
          }
        });

        return false;
    }
     
  }
}
