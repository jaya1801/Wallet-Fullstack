import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Wallet } from 'src/app/Model/wallet';
import { WalletBackendService } from 'src/app/service/wallet-backend.service';
import { WalletService } from 'src/app/service/wallet.service';

@Component({
  selector: 'app-withdraw-funds',
  templateUrl: './withdraw-funds.component.html',
  styleUrls: ['./withdraw-funds.component.css']
})
export class WithdrawFundsComponent {

  
  msg: string = "";
  errorMsg: string = "";

  id: string | null = "";
  wallet: Wallet = new Wallet();

  constructor(private router:Router,private activatedRoute: ActivatedRoute,private walletService: WalletService,private walletBackendService: WalletBackendService) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");
    console.log("Withdraw wallet id:" + this.id);
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

  withdrawFunds(){
    console.log("Withdraw funds from wallet:");
    console.log(this.wallet);
    this.walletBackendService.withdrawFunds(this.wallet).subscribe(
      {
        next:(data)=>{
          this.msg= "amount withdraw successfully";
          this.errorMsg= "";
          //this.router.navigateByUrl("wallets");
        },
        error:(err)=>{
          console.log(err.error);
          this.msg= "";
          this.errorMsg= JSON.stringify(err.error);//"Amount could not be deducted successfully";

        }
      }
    )

    setTimeout(() => {
      this.router.navigateByUrl("/wallets");
    }, 1000);

  }

}
