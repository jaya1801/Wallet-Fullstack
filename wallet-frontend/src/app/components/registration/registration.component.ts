import { Component } from '@angular/core';
import { Register } from 'src/app/Model/register';
import { RegisterWalletComponent } from '../register-wallet/register-wallet.component';
import { Router } from '@angular/router';
import { WalletService } from 'src/app/service/wallet.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  register:Register = new Register();
  show_button: Boolean = false;
  show_eye: Boolean = false;

  constructor(public router:Router, private walletService: WalletService) {}


  displayForm(){
    console.log("displayForm()");
    console.log(this.register);
    this.walletService.addUser(this.register);
    this.router.navigateByUrl("login");
    

  }

  eyeToggle() {
    this.show_button = !this.show_button;
    this.show_eye = !this.show_eye;
  }

  

}
