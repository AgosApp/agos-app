import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Student} from "../auth_service/models/Student";
import {environment} from "../../../environments/environment";
import {Thesis} from "../auth_service/models/thesis";

@Injectable({
  providedIn: 'root'
})
export class ThesisService {

  constructor(private http: HttpClient) { }

  getThesesByStudent(){
    let studentId = localStorage.getItem('user_id')
    console.log("tes "+studentId)
    return this.http.get<Thesis[]>(environment.apiBaseUrl+`students/${studentId}/theses`);
  }
}
