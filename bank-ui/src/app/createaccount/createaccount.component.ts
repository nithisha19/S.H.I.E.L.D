import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-createaccount',
  templateUrl: './createaccount.component.html',
  styleUrls: ['./createaccount.component.css']
})
export class CreateaccountComponent implements OnInit {

  model: Account={
    account_type:'',
    account_balance:null,
    user_id:null,
    bank_id:null
  }
  constructor(private httpClient:HttpClient) { }

  ngOnInit() {
  }
  register() {
    console.log(this.model);
    let url=`http://localhost:8080/olb/bank/${this.model.user_id}/${this.model.bank_id}/addAccount`;
    this.httpClient.post(url,this.model).subscribe(
       res =>{
         
       alert("Account created successfully");
       location.reload();
      },
       err => {
        alert("an error occoured");
      }    
    );
  }

}
export interface Account{
  user_id:number;
  bank_id:number;
  account_type:string,
  account_balance:number,

}