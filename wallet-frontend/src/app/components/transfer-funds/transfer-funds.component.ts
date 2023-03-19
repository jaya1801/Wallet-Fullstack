import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Wallet } from 'src/app/Model/wallet';
import { WalletBackendService } from 'src/app/service/wallet-backend.service';
import { WalletService } from 'src/app/service/wallet.service';

@Component({
  selector: 'app-transfer-funds',
  templateUrl: './transfer-funds.component.html',
  styleUrls: ['./transfer-funds.component.css']
})
export class TransferFundsComponent {

  msg: string = "";
  errorMsg: string = "";

  fromId: string | null = "";
  toId: string|null = "";
  
  amount: string|null ="0";

  constructor(private router:Router,private activatedRoute: ActivatedRoute,private walletService: WalletService,private walletBackendService: WalletBackendService) {}

  ngOnInit(): void {
    this.fromId = this.activatedRoute.snapshot.paramMap.get("id");
    
    console.log("Transfer from id:" + this.fromId);
  }

 transferFunds(){
  console.log("Transfer funds from wallet:");
  console.log(this.fromId,this.toId,this.amount);
  this.walletBackendService.transferFunds(this.fromId,this.toId,this.amount).subscribe(
    {
      next:(data)=>{
        this.msg= "Amount transferred successfully";
        this.errorMsg= "";
        //this.router.navigateByUrl("wallets");
      },
      error:(err)=>{
        console.log(err.error);
        this.msg= "";
        this.errorMsg= JSON.stringify(err.error);//"Amount could not be transferred successfully";
      }
    }
  )

  setTimeout(() => {
    this.router.navigateByUrl("/wallets");
  }, 1000);
  
 }

}
