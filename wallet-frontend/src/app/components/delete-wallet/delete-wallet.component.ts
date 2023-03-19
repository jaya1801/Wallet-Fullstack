import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Wallet } from 'src/app/Model/wallet';
import { WalletBackendService } from 'src/app/service/wallet-backend.service';

@Component({
  selector: 'app-delete-wallet',
  templateUrl: './delete-wallet.component.html',
  styleUrls: ['./delete-wallet.component.css']
})
export class DeleteWalletComponent implements OnInit {

  
  msg: string = "";
  errorMsg: string = "";

  id: string | null = "";

  constructor(private router:Router,private activatedRoute: ActivatedRoute,private walletBackendService: WalletBackendService) {}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");

  }

  deleteWallet(): void {
    console.log("Delete wallet id:" + this.id);
    this.walletBackendService.deleteWalletById(this.id).subscribe(
      {
        next: (data) => { // success
          this.msg = "Wallet Deleted Successfully . Id:" + this.id;
          this.errorMsg = "";
          
        },
        error: () => {
          this.errorMsg = "Wallet Could not be deletd.";
          this.msg = "";
        },
        complete: () => { }
      }
    )

    setTimeout(() => {
      this.router.navigateByUrl("/wallets");
    }, 1000);

  }

}
