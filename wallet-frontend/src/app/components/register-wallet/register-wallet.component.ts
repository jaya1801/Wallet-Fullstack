import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Wallet } from 'src/app/Model/wallet';
import { WalletBackendService } from 'src/app/service/wallet-backend.service';
import { WalletService } from 'src/app/service/wallet.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-wallet',
  templateUrl: './register-wallet.component.html',
  styleUrls: ['./register-wallet.component.css']
})
export class RegisterWalletComponent {

  wallet: Wallet = new Wallet();
  constructor(private router:Router,private walletService:WalletService,private walletBackendService:WalletBackendService){}

  ngOnInit(): void {
    //throw new Error('Method not implemented.');
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
  }

  
  onSubmit(){
    console.log(this.wallet);
    this.walletBackendService.addWallet(this.wallet);

    let empPost: Observable<any> =this.walletBackendService.addWallet(this.wallet);
    empPost.subscribe(
      {
        next:(data)=>{ // executes when back end reposnds with success status code
          console.log(data);
        },
        error:(error)=>{ // executes when back end responds with error status code
          console.log(error);
        },
        complete:()=>{ //
          console.log("Post request Completed...")
        }
      }
    )

    this.router.navigateByUrl("/wallets");
  }

  


}
