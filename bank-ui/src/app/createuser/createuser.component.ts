import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.css']
})
export class CreateuserComponent  implements OnInit{

  
  model: User={
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
  constructor(private httpClient:HttpClient){

  }
  ngOnInit(){
  
  }
  register(form) {
    console.log(form)
    let url="http://localhost:8080/olb/bank/saveuser";
    console.log('priniting the value of model')
    console.log(this.model);
    this.httpClient.post(url,this.model).subscribe(
       res =>{
         console.log(this.model);
         alert("user created");

        // location.reload();
      },
       err => {
        alert("an error occoured");
      }    
    );

  }


  // _keyPress(event: any) {
  //   const pattern = /[0-9]/;
  //   let inputChar = String.fromCharCode(event.charCode);
  //   if (!pattern.test(inputChar)) {
  //       event.preventDefault();

  //   }
}


export interface User{
  first_name:string;
  middle_name:string;
  last_name:string;
  password:string;
  dob:string;
  address:string;
  email:string,
  mobile_no:number,
  gender:string;
  ssn_no:string;
}