import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule, ReactiveFormsModule} from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterWalletComponent } from './components/register-wallet/register-wallet.component';
import { DisplayAllWalletsComponent } from './components/display-all-wallets/display-all-wallets.component';
import { UpdateWalletComponent } from './components/update-wallet/update-wallet.component';

import { LoginComponent } from './components/login/login.component';
import { SearchPipe } from './pipes/search.pipe';
import { SortPipe } from './pipes/sort.pipe';
import {HttpClientModule} from '@angular/common/http';
import { DeleteWalletComponent } from './components/delete-wallet/delete-wallet.component';
import { AddFundsComponent } from './components/add-funds/add-funds.component';
import { WithdrawFundsComponent } from './components/withdraw-funds/withdraw-funds.component';
import { TransferFundsComponent } from './components/transfer-funds/transfer-funds.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';

@NgModule({
  declarations: [

    AppComponent,
    HomeComponent,
    RegisterWalletComponent,
    DisplayAllWalletsComponent,
    UpdateWalletComponent,
    DeleteWalletComponent,
    LoginComponent,
    SearchPipe,
    SortPipe,
    AddFundsComponent,
    WithdrawFundsComponent,
    TransferFundsComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
