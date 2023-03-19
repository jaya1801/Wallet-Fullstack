import { Component, OnInit } from '@angular/core';
import { Wallet } from 'src/app/Model/wallet';
import { Router } from '@angular/router';
import { WalletService } from 'src/app/service/wallet.service';
import { WalletBackendService } from 'src/app/service/wallet-backend.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-display-all-wallets',
  templateUrl: './display-all-wallets.component.html',
  styleUrls: ['./display-all-wallets.component.css']
})
export class DisplayAllWalletsComponent implements OnInit {

  wallets:Wallet[] = [];
  msg: string = "";
  errorMsg: string = "";

  constructor(private router: Router,private walletService: WalletService,private walletBackendService: WalletBackendService){}

  ngOnInit(): void {

    if (localStorage.getItem('auth') == "false") {
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
    }
   
    this.walletBackendService.getAllWallets().subscribe(
      {
        next: (data) => {
          console.log(data);
          this.wallets = data;
        },
        error: (err) => {
          console.log(err);

        },
        complete: () => { }
      }
    )
  }

  deleteWallet(wallet:Wallet){
    console.log("Delete wallet");
    console.log(wallet);
    this.router.navigate(['delete',wallet.id])

  }

  updateWallet(wallet: Wallet) {
    console.log("Update wallet");
    console.log(wallet);
    this.router.navigate(['update', wallet.id]);
  }

  withdrawFunds(wallet:Wallet){
    console.log("Withdraw Funds");
    console.log(wallet);
    this.router.navigate(['withdraw', wallet.id]);

  }

  addFunds(wallet:Wallet){
    console.log("Add Funds");
    console.log(wallet);
    this.router.navigate(['add', wallet.id]);

  }

  transferFunds(wallet:Wallet){
    console.log("Transfer funds");
    console.log(wallet);
    this.router.navigate(['transfer',wallet.id]);
  }


    query:string="";

    field:string='id';

    changeSortByFiled(field:string){
      console.log(field);
      this.field = field;
    }
  

}
