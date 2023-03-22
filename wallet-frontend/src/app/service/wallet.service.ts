import { Injectable, OnInit } from '@angular/core';
import { Wallet } from '../Model/wallet';
import { LogIn } from '../Model/login';
import { Register } from '../Model/register';

@Injectable({
  providedIn: 'root'
})
export class WalletService implements OnInit{

  wallets: Wallet[] = [];
  users: Register[] = [{ username: "user", email: "user123@gmail.com", password: "password" }];

  constructor() { }

  ngOnInit(){
  }

    getAllWallets():Wallet[]{
      return this.wallets;
    }

    addWallet(newWallet:Wallet):void{
      this.wallets.push(newWallet);
    }

    addUser(newRegister: Register) {
      this.users.push(newRegister);
    }

    authenticateUser(newLogin: LogIn) {
      const user = this.users.find((obj) => {
        if (obj.username == newLogin.username && obj.password == newLogin.password) {
          return true;
        }
        else {
          return false;
        }
      });
  
      if (!user) {
        return false;
      }
  
      return true;
    }


}
