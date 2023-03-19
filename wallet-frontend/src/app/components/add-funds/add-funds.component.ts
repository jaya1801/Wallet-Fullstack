import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Wallet } from 'src/app/Model/wallet';
import { WalletBackendService } from 'src/app/service/wallet-backend.service';
import { WalletService } from 'src/app/service/wallet.service';

@Component({
  selector: 'app-add-funds',
  templateUrl: './add-funds.component.html',
  styleUrls: ['./add-funds.component.css']
})
export class AddFundsComponent {

  msg: string = "";
  errorMsg: string = "";

  id: string | null = "";
  wallet: Wallet = new Wallet();

  constructor(private router:Router,private activatedRoute: ActivatedRoute,private walletService: WalletService,private walletBackendService: WalletBackendService) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");
    console.log("Add Funds:" + this.id);
    this.walletBackendService.getWalletById(this.id).subscribe(

      {
        next: (data) => {
          this.wallet = data;
          console.log(data);

        },
        error: (error) => {
          console.log(Error);

        }
      }

    )

  }

  addFunds(){
    console.log("Add funds to wallet:");
    console.log(this.wallet);
    this.walletBackendService.addFunds(this.wallet).subscribe(
      {
        next:(data)=>{
          this.msg= "amount added successfully";
          this.errorMsg= "";
         // this.router.navigateByUrl("wallets");
        },
        error:(err)=>{
          console.log(err.error);
          this.msg= "";
          this.errorMsg= JSON.stringify(err.error);//"Amount could not be added successfully";

        }
      }
    )

    setTimeout(() => {
      this.router.navigateByUrl("/wallets");
    }, 1000);

  }





}
