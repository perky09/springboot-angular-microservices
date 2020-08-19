import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  errorMessage: string;
  //we will create instance for UserService and Router by injecting propetry in constructor see below
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    //we will check session user is present or not 
    if(this.userService.currentUserValue){
      this.router.navigate(['/home']);
      return;
    }
  }

  //we will call authentication service from login method
  login(){
    this.userService.login(this.user).subscribe(data => {
      this.router.navigate(['/profile']);
    }, err => {
      this.errorMessage = "UserName or Password is invalid"
    });

  }

}
