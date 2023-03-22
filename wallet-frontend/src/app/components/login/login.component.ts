import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LogIn } from 'src/app/Model/login';
import { AuthService } from 'src/app/service/auth.service';
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

  // displayLogIn() {
  //   console.log("dispalyLogIn()");
  //   console.log(this.login);
  // }

  constructor(private router:Router, private walletService: WalletService,private authService:AuthService){}


  submitLoginForm(){
    console.log("submitLoginForm()");
    console.log(this.login);
    
    // if user is authorised navigate to home/dashboard page
    // if(this.login.userName=="user" && this.login.password=="user1")
    //   this.router.navigateByUrl("registerWallet");

      // if (this.walletService.authenticateUser(this.login)) {
      //   localStorage.setItem('auth', 'true');
      //   this.router.navigateByUrl("/wallets");
      // }
      // else{
      //   this.errorMsg="User does not exist!";


      // }

      this.authService.userLogin(this.login).subscribe(
        {
          next:(data)=>{
            console.log(data);
            sessionStorage.setItem("user",JSON.stringify(data));
            console.log(data.jwt);
            sessionStorage.setItem("jwt",data.jwt);
            sessionStorage.setItem("role",data.role);
            
            //localStorage
            this.router.navigateByUrl("/wallets");
          },
          error:(err)=>{
            console.log(err);
            //this.errorMsg="User does not exist!";
            this.errorMsg = err;
          }
        }
      );


}
eyeToggle() {
  this.show_button = !this.show_button;
  this.show_eye = !this.show_eye;
}
}
