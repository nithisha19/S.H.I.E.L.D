import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { LoginService } from '../services/login.service';


@Component({
  selector: 'app-titlebar',
  templateUrl: './titlebar.component.html',
  styleUrls: ['./titlebar.component.css']
})
export class TitlebarComponent implements OnInit {


  ngOnInit() {
  }

  constructor(private router: Router, private loginService: LoginService) {
    // on route change to '/login', set the variable showHead to false
    // router.events.forEach((event) => {
    //   if (event instanceof NavigationStart) {
    //     if (event['url'] == '/profile' || event['url'] == '/balance' || event['url'] == '/account' || event['url'] == '/transaction' || event['url'] == '/dashboard') {
    //       this.showHead = false;
    //     }
    //     else {
    //       this.showHead = true
    //     }
    //   }
    // });

  }
  toggle() {
    this.loginService.show = !this.loginService.show
  }
}
