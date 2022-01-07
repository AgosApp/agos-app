import {Injectable, SkipSelf} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {LoginRequest} from "./models/LoginRequest";
import {LoginResponse} from "./models/LoginResponse";
import {Observable} from "rxjs";
import jwt_decode from 'jwt-decode';
import {environment} from "../../../environments/environment";
import {Student} from "./models/Student";
import {Professor} from "./models/professor";
import {JwtHelperService} from "@auth0/angular-jwt";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService) {

  }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {

    console.log("hello");
    const body = new HttpParams()
      .set('username', loginRequest.username)
      .set('password', loginRequest.password);

    return this.http.post<LoginResponse>(environment.apiBaseUrl + "login",
      body.toString(),
      {
        headers: new HttpHeaders()
          .set('Content-Type', 'application/x-www-form-urlencoded')
      }
    );

  }

  static getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    }
    catch (Error) {
      return "Couldn't decode token";
    }
  }

  static getToken(): string {
    return <string>localStorage.getItem('token');
  }

   isAuthenticated(){
    const token = localStorage.getItem("token")

     // @ts-ignore
     return !this.jwtHelper.isTokenExpired(token);
    //return token != null ? true:false;
   }

  isAdmin() {
    let roles = localStorage.getItem("roles")
    if(roles != null)
      return roles.includes("ADMIN_ROLE")
    else return ;
  }

  isProf() {
    let roles = localStorage.getItem("roles")
    if(roles != null)
      return roles.includes("PROF_ROLE")
    else return ;
  }

  isStudent() {
    let roles = localStorage.getItem("roles")
    if(roles != null)
      return roles.includes("STUDENT_ROLE")
    else return ;
  }

  logout(){
    localStorage.setItem("token","")
    localStorage.clear()
  }

  getStudentByUsername(username : string): Observable<Student> {
    return this.http.get<Student>(environment.apiBaseUrl+"students/findByUsername?username="+username);
  }

  getProfessorByUsername(username : string): Observable<Professor> {
    return this.http.get<Professor>(environment.apiBaseUrl+"professors/findByUsername?username="+username);
  }


 /* static getUser(): User | null {
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
