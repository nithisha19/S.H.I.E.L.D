import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  constructor(private httpClient: HttpClient, private loginService: LoginService) {

  }
  ngOnInit() {
    this.getAccounts();
  }
  account_id = this.loginService.currentId
  account = []
  update(form) {
    console.log(form)

  }
  public getAccounts() {

    let user_id = this.loginService.currentId;
    let url = `http://localhost:8080/olb/customer/accounts/${user_id}`;
    this.httpClient.get<Account[]>(url).subscribe(
      res => {
        console.log(res[0]["user"])
        this.account = res[0]["user"]
      },
      err => {
        alert("no accounts to display");
      }
    );
  }
}
