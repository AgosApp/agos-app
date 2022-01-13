import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Thesis} from "../../models/thesis";

@Injectable({
  providedIn: 'root'
})
export class ThesisService {

  constructor(private http: HttpClient) { }

  getThesesByStudent(userId: any){
    return this.http.get<Thesis[]>(environment.apiBaseUrl+`students/${userId}/theses`);
  }

  getThesisByStudent(userId: any, thesisId: any){
    return this.http.get<Thesis>(environment.apiBaseUrl+`students/${userId}/theses/${thesisId}`)
  }
}
