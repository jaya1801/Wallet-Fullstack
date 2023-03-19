import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LogIn } from 'src/app/Model/login';
import { WalletService } from 'src/app/service/wallet.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  
  login: LogIn = new LogIn();
  show_button: Boolean = false;
  show_eye: Boolean = false;
  errorMsg: string = "";

  displayLogIn() {
    console.log("dispalyLogIn()");
    console.log(this.login);
  }

  constructor(private router:Router, private walletService: WalletService){}


  submitLoginForm(){
    console.log("submitLoginForm()");
    
    // if user is authorised navigate to home/dashboard page
    // if(this.login.userName=="user" && this.login.password=="user1")
    //   this.router.navigateByUrl("registerWallet");

      if (this.walletService.authenticateUser(this.login)) {
        localStorage.setItem('auth', 'true');
        this.router.navigateByUrl("/wallets");
      }
      else{
        this.errorMsg="User does not exist!";


      }


}
eyeToggle() {
  this.show_button = !this.show_button;
  this.show_eye = !this.show_eye;
}
}
