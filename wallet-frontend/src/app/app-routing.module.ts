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
import { ProfileComponent } from './components/profile/profile.component';
import { GaurdService } from './service/gaurd.service';



const routes: Routes = [
  { path: '',   redirectTo: '/home', pathMatch: 'full' }, // redirect to `first-component`
  {path: 'registerWallet',component:RegisterWalletComponent,canActivate:[GaurdService]},
  {path:'wallets', component:DisplayAllWalletsComponent,canActivate:[GaurdService]},
  {path:'update/:id',component:UpdateWalletComponent,canActivate:[GaurdService]},
  {path: 'login',component:LoginComponent},
  {path: 'delete/:id',component:DeleteWalletComponent,canActivate:[GaurdService]},
  {path: 'withdraw/:id',component:WithdrawFundsComponent,canActivate:[GaurdService]},
  {path: 'add/:id',component:AddFundsComponent,canActivate:[GaurdService]},
  {path: 'transfer/:id',component:TransferFundsComponent,canActivate:[GaurdService]},
  {path: 'home',component:HomeComponent},
  {path: 'registration',component:RegistrationComponent},
  {path: 'profile',component:ProfileComponent,canActivate:[GaurdService]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
