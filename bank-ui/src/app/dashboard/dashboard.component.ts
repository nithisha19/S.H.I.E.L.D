import { Component, OnInit } from '@angular/core';
import {TimeoutService} from '../timeout.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
 
  router: any;

  constructor(private timeOutService:TimeoutService) { }
 

  ngOnInit() {
  
   this.timeOutService.inactivityTime();
  }

}
