import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Formation} from "../models/formation.model";
import {environment} from "../../environments/environment";
import {Session} from "../models/session.model";

@Injectable({
  providedIn: 'root'
})
export class FormationsService {

  constructor(private httpClient:HttpClient) { }

  getFormations(department_id:any){
    return this.httpClient.get(environment.apiBaseUrl+'departments/'+department_id+'/formations');
    console.log('i am in service, this is the department' +department_id);
  }

  addFormation(department_id:any, data: any){
    return this.httpClient.post(environment.apiBaseUrl+'departments/'+department_id+'/formations', data);

  }

  deleteFormation(department_id:any, id:any){
    return this.httpClient.delete(environment.apiBaseUrl+'departments/'+department_id+'/formations/'+id);
  }

  getNotationGroups(){
    return this.httpClient.get(environment.apiBaseUrl+'notationGroups');

  }

  addSession(formation_id:any, notationGroupId:number, data:any){
    return this.httpClient.post(environment.apiBaseUrl+'formations/'+formation_id+'/sessions?notationGroupId='+ notationGroupId, data);
  }

}
