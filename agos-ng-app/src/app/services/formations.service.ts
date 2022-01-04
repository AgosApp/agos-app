import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Formation} from "../models/formation.model";

@Injectable({
  providedIn: 'root'
})
export class FormationsService {

  constructor(private httpClient:HttpClient) { }

  getFormations(){
    return this.httpClient.get('http://127.0.0.1:8080/api/departments/1/formations');
  }

  addFormation(data: any){
    return this.httpClient.post('http://127.0.0.1:8080/api/departments/1/formations', data);

  }

  deleteFormation(id:any){
    return this.httpClient.delete('http://127.0.0.1:8080/api/departments/1/formations/'+id);
  }


}
