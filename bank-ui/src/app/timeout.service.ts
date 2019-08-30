import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {LoginService} from './login.service'

@Injectable({
  providedIn: 'root'
})
export class TimeoutService {

  constructor(private router: Router,private loginService:LoginService) { }
  inactivityTime() {
    let time;
    let scopeObj = this;
    window.onload = resetTimer;
    // DOM Events
    document.onmousemove = resetTimer;
    document.onkeypress = resetTimer;
    function logout() {
      document.onmousemove = null;
      document.onkeypress = null;
      scopeObj.loginService.show = true
      scopeObj.router.navigateByUrl('/login');
      alert("Please login again!");
    }

    function resetTimer() {
      clearTimeout(time);
      time = setTimeout(logout, 180000)
      // 1000 milliseconds = 1 second
    }
  };
}

