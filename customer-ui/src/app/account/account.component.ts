import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../services/login.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  constructor(private httpClient: HttpClient, private loginService: LoginService, private router: Router) { }
  accounts: Account[] = []
  user_id = this.loginService.currentId;
  savings_img: string = '../../assets/savings.jpg'
  current_img: string = '../../assets/current.png'

  ngOnInit() {
    if (this.loginService.show == true)
      this.router.navigate([`home`]);
    else
      this.viewsAccounts();
  }
  public viewsAccounts() {

    let user_id = this.loginService.currentId;
    let url = `http://localhost:8080/olb/customer/accounts/${user_id}`;
    this.httpClient.get<Account[]>(url).subscribe(
      res => {
        this.accounts = res;
      },
      err => {
        // alert("no accounts to display");
        console.log("no accountrs", err)
      }
    );
  }
  open(accountDetails) {
    console.log("OPENING", accountDetails);
    this.router.navigate([`transaction/${accountDetails.account_no}`]);
  }
  mini(accountDetails) {
    console.log("VIEW", accountDetails);
    this.router.navigate([`ministatement/${accountDetails.account_no}`]);
  }
  transfer(account) {
    this.router.navigate([`transfer/${account.account_no}`]);
  }
}
export interface Account {
  user_id: number;
  account_type: string;
  account_no: number;
  account_balance: number;
}
