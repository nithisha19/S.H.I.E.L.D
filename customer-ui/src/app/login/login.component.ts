import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']

})
export class LoginComponent {
  err_msg: boolean = false

  constructor(private router: Router, private loginService: LoginService, private httpClient: HttpClient) {

  }

  validate(form) {
    var payload = {
      id: form.value.account_id,
      password: form.value.password

    };
    let url = `http://localhost:8080/olb/customer/login`;
    this.httpClient.post(url, payload).subscribe(
      res => {
        if (res != null) {
          console.log(form)
          this.loginService.show = false
          this.loginService.currentId = form.value.account_id
          this.router.navigateByUrl('/dashboard');
        } else {
          this.err_msg = true
        }
      },
      err => {
        alert("an error occoured");
      }
    );

  }


}