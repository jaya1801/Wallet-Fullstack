import { Component } from '@angular/core';
import { Register } from 'src/app/Model/register';
import { RegisterWalletComponent } from '../register-wallet/register-wallet.component';
import { Router } from '@angular/router';
import { WalletService } from 'src/app/service/wallet.service';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  register:Register = new Register();
  show_button: Boolean = false;
  show_eye: Boolean = false;

  constructor(public router:Router,private authService:AuthService) {}


  displayForm(){
    // console.log("displayForm()");
    // console.log(this.register);
    // this.walletService.addUser(this.register);
    // this.router.navigateByUrl("login");

    this.register.role = "user";
    console.log(this.register);
    
    this.authService.registerUser(this.register).subscribe(
      {
        next:(data)=>{
          console.log(data);
          //localStorage
          this.router.navigateByUrl("login");
        },
        error:(err)=>{
          console.log(err);
          //this.errorMsg="User does not exist!";
          console.log(err);
        }
      }
    );
    

  }

  eyeToggle() {
    this.show_button = !this.show_button;
    this.show_eye = !this.show_eye;
  }

  

}
