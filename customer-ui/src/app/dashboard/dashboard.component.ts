import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { TimeoutService } from '../timeout.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router, private timeOutService: TimeoutService) { }

  ngOnInit() {

    if (this.loginService.show == true)
      this.router.navigate([`home`]);
    else
      this.timeOutService.inactivityTime();
  }

}
