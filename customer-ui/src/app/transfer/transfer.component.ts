import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {
  from_id = '';
  IsTransfer: boolean = false
  funds: boolean = true;
  model: Transfer = {
    from_account: null,
    to_account: null,
    amount: null

  }
  IsPending: boolean = false;

  constructor(private httpClient: HttpClient, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe(params => {
      console.log("INSIDE TRANFER", params.id)
      this.from_id = "From account: " + params.id;
      this.model.from_account = (params.id)
    });

  }

  ngOnInit() {



  }

  transfer(form) {
    // this.show = true
    this.IsTransfer = false
    this.IsPending = false
    this.funds = true
    let url = `http://localhost:8080/olb/customer/transfer`;
    console.log(this.model)
    this.httpClient.post(url, this.model).subscribe(resp => {
      console.log(resp)
      if (resp["statusCode"] == 200)
        this.IsTransfer = true;
      else if (resp["statusCode"] == 402)
        this.IsPending = true;
      else if (resp["statusCode"] == 401)
        this.funds = false;
      else
        this.IsTransfer = false;
      console.log(resp)
      // location.reload();
      console.log(form)
      form.reset();
      // this.IsPending = false
      // this.IsTransfer = false

    }
    );
  }


}
export interface Transfer {

  from_account: string;
  to_account: string;
  amount: string;
}
