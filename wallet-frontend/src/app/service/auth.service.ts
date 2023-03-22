import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LogIn } from '../Model/login';
import { Register } from '../Model/register';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient) { }

  userLogin(user:LogIn):Observable<any>{
    console.log(user);
    return this.httpClient.post("http://localhost:8090/auth/login",user,{responseType:'json'});
  }
  
  getUserInfo():Observable<any>{

    let jwt = sessionStorage.getItem("jwt");

    var reqHeader = new HttpHeaders({ 
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${jwt}`
   });
   return this.httpClient.get("http://localhost:8090/auth/userinfo", { headers: reqHeader })

  }

  registerUser(user:Register){
    console.log(user);
    return this.httpClient.post("http://localhost:8090/auth/user",user,{responseType:'text'});
    
  }
}
