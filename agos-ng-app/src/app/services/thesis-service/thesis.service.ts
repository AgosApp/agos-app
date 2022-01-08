import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Student} from "../../models/Student";
import {environment} from "../../../environments/environment";
import {Thesis} from "../../models/thesis";

@Injectable({
  providedIn: 'root'
})
export class ThesisService {

  constructor(private http: HttpClient) { }

  getThesesByStudent(){
    let studentId = localStorage.getItem('user_id')
    return this.http.get<Thesis[]>(environment.apiBaseUrl+`students/${studentId}/theses`);
  }
}
