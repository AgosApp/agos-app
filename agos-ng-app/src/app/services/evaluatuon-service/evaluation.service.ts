import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Thesis} from "../../models/thesis";
import {environment} from "../../../environments/environment";
import {Evaluation} from "../../models/evaluation";

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  constructor(private http: HttpClient) { }

  getEvaluationsByProfessor(userId : any){
    return this.http.get<Evaluation[]>(environment.apiBaseUrl+`professors/${userId}/evaluations`);
  }

  getEvaluationsByThesis(thesisId: any){
    return this.http.get<Evaluation[]>(environment.apiBaseUrl+`theses/${thesisId}/evaluations`);
  }
}
