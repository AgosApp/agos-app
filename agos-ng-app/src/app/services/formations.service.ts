import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Formation} from "../models/formation.model";

@Injectable({
  providedIn: 'root'
})
export class FormationsService {

  constructor(private httpClient:HttpClient) { }

  getFormations(department_id:any){
    return this.httpClient.get('http://127.0.0.1:8080/api/departments/'+department_id+'/formations');
    console.log('i am in service, this is the department' +department_id);
  }

  addFormation(department_id:any, data: any){
    return this.httpClient.post('http://127.0.0.1:8080/api/departments/'+department_id+'/formations', data);

  }

  deleteFormation(department_id:any, id:any){
    return this.httpClient.delete('http://127.0.0.1:8080/api/departments/'+department_id+'/formations/'+id);
  }


}
