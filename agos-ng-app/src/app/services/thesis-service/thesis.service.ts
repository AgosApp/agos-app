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

  getThesisByProfessor(userId: any, thesisId: any){
    return this.http.get<Thesis>(environment.apiBaseUrl+`professors/${userId}/theses/${thesisId}`)
  }
  getThesisByCrenal(crenalId: any){
    return this.http.get<Thesis>(environment.apiBaseUrl+`creneaux/${crenalId}/theses`)
  }
  addThesis(crenalId: any,roomId : any,student : any,professor : any,data: Thesis){
    return this.http.post(environment.apiBaseUrl+`creneaux/${crenalId}/theses?roomId=`+roomId+`&students=`+student+`&professors=`+professor+`&typesProfessors=Tuteur,Temoin`,data)
  }
}
