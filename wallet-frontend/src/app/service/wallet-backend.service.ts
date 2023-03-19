import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Wallet } from '../Model/wallet';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class WalletBackendService {

  constructor(private httpClient:HttpClient ) { }

  addWallet(newWallet:Wallet):Observable<any>{
    let url:string = 'http://localhost:8090/v1/wallet';
    return this.httpClient.post(url,newWallet,{responseType:'json'});
  }

  getWalletById(id:string|null):Observable<any>{
    return this.httpClient.get('http://localhost:8090/v1/wallet/' +id);
  }

  deleteWalletById(id:string|null):Observable<any>{
    return this.httpClient.delete("http://localhost:8090/v1/wallet/"+id);
  }

  updateWallet(wallet:Wallet):Observable<any>{
    return this.httpClient.put("http://localhost:8090/v1/wallet/"+wallet.id,wallet,{responseType:'json'});
  }


  getAllWallets():Observable<any>{
    return this.httpClient.get("http://localhost:8090/v1/wallets");
  }

  withdrawFunds(wallet: Wallet):Observable<any>{
    return this.httpClient.patch(`http://localhost:8090/v1/wallet/${wallet.id}/withdrawFund/${wallet.amount}`,{resposeType:'json'});
    
  }

  addFunds(wallet:Wallet):Observable<any>{
    return this.httpClient.patch(`http://localhost:8090/v1/wallet/${wallet.id}/addFund/${wallet.amount}`,{resposeType:'json'});

  }

  transferFunds(fromId:string|null,toId:string|null,amount:string|null):Observable<any>{
    return this.httpClient.patch(`http://localhost:8090/v1/wallet/${fromId}/${toId}/fundTransfer/${amount}`,{responseType:'json'});
  }

}
