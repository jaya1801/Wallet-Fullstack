import { Component } from '@angular/core';
import { Register } from 'src/app/Model/register';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  imageSrc: string = "../assets/images/ProfileIcon.png";

  user:Register = new Register();

  constructor(private authService:AuthService){}

  ngOnInit(): void {
    this.authService.getUserInfo().subscribe(
      {
        next:(data)=>{
          console.log(data);  
          this.user= data;

        },
        error:(err)=>{
          console.log(err);
        }
      }
    )
  }

}
