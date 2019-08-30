import { Component, OnInit, Input } from '@angular/core';
import { User } from '../viewusers/viewusers.component';
import { HttpClient } from '@angular/common/http';

import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-modifyuser',
  templateUrl: './modifyuser.component.html',
  styleUrls: ['./modifyuser.component.css']
})
export class ModifyuserComponent implements OnInit {

  user: User; 

  model: User={
    user_id:null,
    first_name:'',
    middle_name:'',
    last_name:'',
    password:'',
    dob:'',
    address:'',
    email:'',
    mobile_no:null,
    gender:'',
    ssn_no:''
  };
  constructor(private httpClient:HttpClient,private router:Router,private activatedRoute:ActivatedRoute) {
    this.model.user_id=this.activatedRoute.snapshot.params.id;

    this.getDetails(this.activatedRoute.snapshot.params.id);

   }

  ngOnInit() {
      // this.getDetails(this.user);
  }

  public getDetails(id){
    let url=`http://localhost:8080/olb/bank/users/${id}`;
    // console.log('priniting the value of model')
    // console.log(this.model);
    this.httpClient.get<User>(url).subscribe(
       res =>{
         this.user=res;
         console.log(this.user);
         this.model=res;
        
      },
       err => {
        alert("an error occoured");
      }    
    );
  
  }
  public savechanges(){
    console.log(this.model);
    let url=`http://localhost:8080/olb/bank/updateUserDetails`;
    this.httpClient.post(url,this.model).subscribe(
       res =>{
         
       alert("User Details Modified");
      
      },
       err => {
        alert("an error occoured");
      }    
    );
  }
  }

export interface User{
  user_id:number,
  first_name:string,
  middle_name:string,
  last_name:string,
  password:string,
  dob:string,
  address:string,
  email:string,
  mobile_no:number,
  gender:string,
  ssn_no:string
}