import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterWalletComponent } from './components/register-wallet/register-wallet.component';
import { DisplayAllWalletsComponent } from './components/display-all-wallets/display-all-wallets.component';
import { UpdateWalletComponent } from './components/update-wallet/update-wallet.component';
import { DeleteWalletComponent } from './components/delete-wallet/delete-wallet.component';
import { LoginComponent } from './components/login/login.component';
import { WithdrawFundsComponent } from './components/withdraw-funds/withdraw-funds.component';
import { AddFundsComponent } from './components/add-funds/add-funds.component';
import { TransferFundsComponent } from './components/transfer-funds/transfer-funds.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';



const routes: Routes = [
  { path: '',   redirectTo: '/home', pathMatch: 'full' }, // redirect to `first-component`
  {path: 'registerWallet',component:RegisterWalletComponent},
  {path:'wallets', component:DisplayAllWalletsComponent},
  {path:'update/:id',component:UpdateWalletComponent},
  {path: 'login',component:LoginComponent},
  {path: 'delete/:id',component:DeleteWalletComponent},
  {path: 'withdraw/:id',component:WithdrawFundsComponent},
  {path: 'add/:id',component:AddFundsComponent},
  {path: 'transfer/:id',component:TransferFundsComponent},
  {path: 'home',component:HomeComponent},
  {path: 'registration',component:RegistrationComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
