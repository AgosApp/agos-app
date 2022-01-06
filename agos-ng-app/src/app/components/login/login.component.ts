import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth_service/auth.service";
import {LoginRequest} from "../../services/auth_service/models/LoginRequest";
import {LoginResponse} from "../../services/auth_service/models/LoginResponse";
import { MatSnackBar } from '@angular/material/snack-bar';

import {NavbarComponent} from "../navbar/navbar.component";



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
  token : string;
  isAthenticated = false;

  @Input() navbar : NavbarComponent | undefined;


  constructor(private formBuilder: FormBuilder, public dialog: MatDialog, private router:Router,private authService : AuthService,
              private matSnackBar: MatSnackBar
              ) {

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.pattern(this.usernameRegx)])],
      password: ['', Validators.compose([Validators.required])]
    });

    this.loginRequest = { username : "", password : ""};
    this.loginResponse = { access_token : "", refresh_token : ""};
    this.token = '';


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
      loginResponse => {
        this.isAthenticated = true;
        localStorage.setItem("is_authenticated", String(this.isAthenticated))
        this.token = loginResponse.access_token;
        const bearerToken = `Bearer ${loginResponse.access_token}`;
        localStorage.setItem('token', this.token);
        localStorage.setItem('bearer_token', bearerToken);
      },
      error =>  this.matSnackBar.open("Echec lors de l'authentification", "Fermer", {
        duration: 6000,
      }),
      () => {
       const decode_token = AuthService.getDecodedAccessToken(this.token);
       console.log(decode_token.roles)
        localStorage.setItem('roles',decode_token.roles);
       if(decode_token.roles.includes("PROF_ROLE")) {
         this.router.navigateByUrl("department");
         this.authService.getProfessorByUsername(decode_token.sub).subscribe(
           professor => {console.log(professor)
             localStorage.setItem('user_id',String(professor.id));
             localStorage.setItem('username',professor.username);
             localStorage.setItem('firstname',professor.firstName);
             localStorage.setItem('lastname',professor.lastName);
             localStorage.setItem('department_id',String(professor.department.id));
             localStorage.setItem('department_name',professor.department.name);
           },
           error => {
             this.matSnackBar.open("Echec lors de la récupération de vos données", "Fermer", {
               duration: 6000,
             })
           },
           ()=> {
           }
         )
       }
        if(decode_token.roles.includes("STUDENT_ROLE")) {
          this.router.navigateByUrl("");
          console.log(decode_token.sub);

         this.authService.getStudentByUsername(decode_token.sub).subscribe(
           student => {console.log(student);
             localStorage.setItem('user_id',String(student.id));
             localStorage.setItem('username',student.username);
             localStorage.setItem('firstname',student.firstName);
             localStorage.setItem('lastname',student.lastName);
             localStorage.setItem('formation_id',String(student.formation.id));
             localStorage.setItem('department_name',String(student.formation.name));
           },
           error => {
             this.matSnackBar.open("Echec lors de la récupération de vos données", "Fermer", {
               duration: 6000,
             })
           },
           ()=> {}
         )
        }

        this.matSnackBar.open("Authentification terminée avec succès", "Fermer", {
          duration: 6000,
        });
      }
    )

  }
}