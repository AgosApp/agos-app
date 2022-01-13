import { Injectable } from '@angular/core';
import {Evaluation} from "../../models/evaluation";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {CriteriaEvaluation} from "../../models/criteriaEvaluation";

@Injectable({
  providedIn: 'root'
})
export class CriteriaEvaluationService {

  constructor(private http: HttpClient) { }

  getCriteriaEvaluationsByThesis(thesisId: any){
    return this.http.get<CriteriaEvaluation[]>(environment.apiBaseUrl+`theses/${thesisId}/criteriaEvaluations`);
  }

}
