import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class SessionsService {

  constructor(private httpClient:HttpClient) { }

  getSessionsByFormation(formation_id:any){
    return this.httpClient.get('http://127.0.0.1:8080/api/formations/'+formation_id+'/sessions');
    console.log('i am in service, this is the department' +formation_id);
  }
  

}
