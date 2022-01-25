import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Crenel } from 'src/app/models/crenel';
import { Room } from 'src/app/models/room';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CrenalService {

  constructor(private httpClient : HttpClient) { }
  getCrenals(session_id:any){
    return this.httpClient.get(environment.apiBaseUrl+'sessions/'+session_id+'/crenaux');
  }
  addCrenal(session_id:any,rooms:any,data : Crenel){
    return this.httpClient.post(environment.apiBaseUrl+'sessions/'+session_id+'/crenaux?rooms='+rooms,data);
  }
  
}
