import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth_service/auth.service";
import {LoginRequest} from "../../services/auth_service/models/LoginRequest";
import {LoginResponse} from "../../services/auth_service/models/LoginResponse";

import { switchMap, map, catchError, tap } from 'rxjs/operators';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup ;
  usernameRegx = /^[A-Za-z]+$/;
  loginRequest : LoginRequest;
  loginResponse : LoginResponse;

  constructor(private formBuilder: FormBuilder, public dialog: MatDialog, private router:Router,private authService : AuthService) {

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.pattern(this.usernameRegx)])],
      password: ['', Validators.compose([Validators.required])]
    });

    this.loginRequest = { username : "", password : ""};
    this.loginResponse = { accessToken : "", RefreshToken : ""};

    //this.buildForm();

  }

  ngOnInit() {
  }

  buildForm() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.pattern(this.usernameRegx)])],
      password: ['', Validators.compose([Validators.required])]
    });

  }

  login() {
    if (!this.loginForm.valid) {
      return;
    }
    console.log(this.loginForm.value);

  this.loginRequest = this.loginForm.value;

    this.authService.login(this.loginRequest).subscribe(
    data => console.log("data received"),
      error => console.log("error occurred"),
      () => console.log("")
    )

  }
}
