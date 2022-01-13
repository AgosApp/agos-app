import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Criteria } from 'src/app/models/criteria';
import { NotationGroup } from 'src/app/models/notationGroup';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotationService {

  constructor(private http: HttpClient) { }

  getNotationGroups(){
    return this.http.get(environment.apiBaseUrl+'notationGroups');
  }
  getCriterias(){
    return this.http.get(environment.apiBaseUrl+'criterias');
  }

  addCriteria(data:Criteria){
    return this.http.post(environment.apiBaseUrl+'criterias',data);
  }

  deleteCriteria(criteriaId:number){
    return this.http.delete(environment.apiBaseUrl+`criterias/${criteriaId}`);
  }

  createGroup(data:NotationGroup){
    return this.http.post(environment.apiBaseUrl+`notationGroups`,data);
  }

}
