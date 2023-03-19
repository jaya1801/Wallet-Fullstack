import { Component, OnInit } from '@angular/core';
import { Wallet } from 'src/app/Model/wallet';
import { WalletService } from 'src/app/service/wallet.service';
import { ActivatedRoute, Router } from '@angular/router';
import { WalletBackendService } from 'src/app/service/wallet-backend.service';

@Component({
  selector: 'app-update-wallet',
  templateUrl: './update-wallet.component.html',
  styleUrls: ['./update-wallet.component.css']
})
export class UpdateWalletComponent implements OnInit {

  msg: string = "";
  errorMsg: string = "";

  id: string | null = "";
  wallet: Wallet = new Wallet();

  constructor(private router:Router,private activatedRoute: ActivatedRoute,private walletService: WalletService,private walletBackendService: WalletBackendService) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");
    console.log("update id:" + this.id);
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

  updateWallet(){
    console.log("Update wallet:");
    console.log(this.wallet);
    this.walletBackendService.updateWallet(this.wallet).subscribe(
      {
        next:(data)=>{
          this.msg= "Wallet updated successfully";
          this.errorMsg= "";
         // this.router.navigateByUrl("wallets");
        },
        error:(err)=>{
          console.log(err.error);
          this.msg= "";
          this.errorMsg= JSON.stringify(err.error);//"Wallet could not be updated successfully";

        }
      }
    )

    setTimeout(() => {
      this.router.navigateByUrl("/wallets");
    }, 1000);
  }


}
