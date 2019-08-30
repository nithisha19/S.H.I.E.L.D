import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router,private httpClient:HttpClient,private loginService:LoginService) {

  }
  flag = true
  validate(form) {
    var payload={
      id : form.value.account_id,
      password : form.value.password

    };
    let url=`http://localhost:8080/olb/bank/login`;
    this.httpClient.post(url,payload).subscribe(
       res =>{
        if(res!=null){
          this.loginService.show=false;
          console.log("INside login",this.loginService.show)
        this.router.navigateByUrl('/dashboard');
        
        } else {
          alert("invalid useranme or password");
        }          
      },
       err => {
        alert("an error occoured");
      }    
    );
  }
}
