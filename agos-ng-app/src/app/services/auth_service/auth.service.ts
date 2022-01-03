import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {LoginRequest} from "./models/LoginRequest";
import {LoginResponse} from "./models/LoginResponse";
import {Observable} from "rxjs";
//import jwt_decode from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {

  }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {

    console.log("hello");
    const body = new HttpParams()
      .set('username', loginRequest.username)
      .set('password', loginRequest.password);

    return this.http.post<any>("http://localhost:8080/api/login",
      body.toString(),
      {
        headers: new HttpHeaders()
          .set('Content-Type', 'application/x-www-form-urlencoded')
      }
    );

  }

  /*static getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return null;
    }
  }

  static getUser(): User | null {
    const token = localStorage.getItem('token') ? localStorage.getItem('token').split(' ')[1] : null;

    let user :User={
      username:localStorage.getItem('username'),
      firstname:localStorage.getItem('firstname'),
      lastname:localStorage.getItem('lastname'),
      email:localStorage.getItem('email'),
      roles:[],
      dob:'',
    }

    return token ? user : null;
  }*/
}
