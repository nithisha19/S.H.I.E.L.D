import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  show: boolean = true
  currentId: string;
  constructor() { }
}
