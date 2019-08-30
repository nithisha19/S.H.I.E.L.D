import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-viewaccounts',
  templateUrl: './viewaccounts.component.html',
  styleUrls: ['./viewaccounts.component.css']
})
export class ViewaccountsComponent implements OnInit {
  accounts:Account[]=[];

  constructor(private httpClient:HttpClient) { }

  ngOnInit() {
    this.getAllAccounts();
  }
  public getAllAccounts(){
    let url="http://localhost:8080/olb/bank/accounts/21"
    this.httpClient.get<Account[]>(url).subscribe(
      res =>{
        console.log(res);
        this.accounts=res;
      },
      err=>{
        alert("cannot find accounts");
      }
    );
  }
 
  public deleteAccount(account){
    let url=`http://localhost:8080/olb/bank/${account.account_no}/deleteAccount`;
    this.httpClient.delete<Account[]>(url).subscribe(
      res =>{
      
        this.getAllAccounts();

      },
      err=>{
        alert("User cannot be deleted");
      }
    );
  }

}
export interface Account{
  account_no:number,
  account_type:string, 
  opened_date:string,
  user_id:number
}