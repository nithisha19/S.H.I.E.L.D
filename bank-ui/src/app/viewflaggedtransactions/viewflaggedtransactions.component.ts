import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-viewflaggedtransactions',
  templateUrl: './viewflaggedtransactions.component.html',
  styleUrls: ['./viewflaggedtransactions.component.css']
})
export class ViewflaggedtransactionsComponent implements OnInit {

  model: Transaction={
    transaction_id:null,
    from_account:null,
    to_account:null,
    amount:null,
    status:'',
    transaction_date:''
  }
  transactions: Transaction[] = [];
  constructor(private httpClient:HttpClient) { }

  ngOnInit() {
    this.getFlaggedTransactions();
  }
  public getFlaggedTransactions(){
    let url=`http://localhost:8080/olb/bank/pendingTransactions`;
    this.httpClient.get<Transaction[]>(url).subscribe(
      res =>{
        this.transactions=res;
      },
      err=>{
        alert("No pending transactions");
      }
    );
  }
  public approve(transaction){
    let url=`http://localhost:8080/olb/bank/initiatePendingTransfer/${transaction.transaction_id}`;
    this.httpClient.post(url,this.model).subscribe(
      res =>{
        alert("Transaction initiated successfully");
        this.getFlaggedTransactions();
      },
      err=>{
        alert("an error occured");
      }
    );
  }
  public disapprove(transaction){
    let url=`http://localhost:8080/olb/bank/dismiss/${transaction.transaction_id}`;
    this.httpClient.post(url,this.model).subscribe(
      res =>{
        alert("Transaction Cancelled");
        this.getFlaggedTransactions();
      },
      err=>{
        alert("an error occured");
      }
    );
  }
}
export interface Transaction{

  transaction_id:number;
  from_account:number;
  to_account:number;
  amount:number;
  status:string;
  transaction_date:string;
}