import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { TablePagination } from '@material-ui/core';
import { NgxDaterangepickerMd } from 'ngx-daterangepicker-material';
import * as moment from 'moment';




@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  transactions: Transaction[] = [];
  transactions_copy: Transaction[] = [];


  constructor(private httpClient: HttpClient, private activatedRoute: ActivatedRoute) {

  }
  accountNumber: number;
  ngOnInit() {

    this.activatedRoute.params.subscribe(params => {
      console.log(params.id)
      this.accountNumber = params.id
      this.viewLast10Transactions(params.id);
    });

  }

  public viewLast10Transactions(id) {
    let url = `http://localhost:8080/olb/customer/${id}/statements`;

    this.httpClient.get<Transaction[]>(url).subscribe(
      res => {
        this.transactions = res;
        this.transactions_copy = res;

        console.log("DATA LOADING")
        console.log(res)
      },
      err => {
        alert("cannot display trasactions");
      }
    );
  }
  Reset(event) {
    console.log(event)
  }
  onChange(value: any) {
    console.log(value);
  }

  copy = this.transactions;
  searchAccount(ev) {
    // let value = ev.target.value
    // this.transactions = this.copy
    // let tempArray = this.transactions.filter(account => {
    //   // console.log(value.includes('gou'))
    //   if (account.to_account.includes(value)) {
    //     return account;
    //   }
    // })
    // this.transactions = tempArray
  }
  onDateRangeChanged(value) {
    if (value == null || value["endDate"] == null || value["startDate"] == null) {
      this.transactions = this.transactions_copy
    }
    else {
      var i, str = '';
      console.log(value.startDate._d)
      let Startdate = JSON.stringify(value.startDate._d)

      Startdate = Startdate.slice(1, 11)
      let enddate = JSON.stringify(value.endDate._d)
      enddate = enddate.slice(1, 11)
      console.log(Startdate, enddate)
      // console.log(this.formatDate(new Date(Startdate)))
      // this.filterResponseByDate(Startdate, enddate)
      this.filterResponseByDate(moment(value.startDate._d).format('YYYY-MM-DD'), moment(value.endDate._d).format('YYYY-MM-DD'))

    }
  }


  filterResponseByDate(Startdate, enddate) {
    let filteredData = []
    // console.log(this.transactions)
    console.log(Startdate, enddate)
    this.transactions = this.transactions_copy
    // console.log(Startdate, enddate)
    filteredData = this.transactions.filter(function (d) {
      let dateObj = new Date(d.transaction_date)
      let mom = moment(new Date(dateObj)).format('YYYY-MM-DD');
      // console.log("start=", new Date(Startdate), "end=", new Date(enddate), "curr=", new Date(mom))
      return ((new Date(mom) >= new Date(Startdate)) && (new Date(mom) <= new Date(enddate)));
    });
    console.log(filteredData)
    this.transactions = filteredData
  }
}
export interface Transaction {

  transaction_id: number;
  from_account: number;
  to_account: number;
  amount: number;
  status: string;
  transaction_date: string;
}