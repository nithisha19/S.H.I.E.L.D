import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewusers',
  templateUrl: './viewusers.component.html',
  styleUrls: ['./viewusers.component.css']
})
export class ViewusersComponent implements OnInit {
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
  
  users:User[]=[];

  constructor(private httpClient:HttpClient,private router:Router) { }

  ngOnInit() {
    this.getAllUsers();
  }
  public getAllUsers(){
    let url="http://localhost:8080/olb/bank/customers"
    this.httpClient.get<User[]>(url).subscribe(
      res =>{
        this.users=res;
      },
      err=>{
        alert("cannot find users");
      }
    );
  }
  // public deleteUser(user){
  //   for(var i = 0; i < this.users.length; i++) {
  //     if(this.users[i].user_id == user.user_id) {
  //         this.users.splice(i, 1);
  //         break;
  //     }
  // }
  // }
  public deleteUser(user){
    let url=`http://localhost:8080/olb/bank/${user.user_id}/deleteUser`;
    this.httpClient.delete<User[]>(url).subscribe(
      res =>{
        this.getAllUsers();

      },
      err=>{
        alert("User cannot be deleted");
      }
    );
  }
  public modify(user){
    this.router.navigate([`/modifyuser/${user.user_id}`]);

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