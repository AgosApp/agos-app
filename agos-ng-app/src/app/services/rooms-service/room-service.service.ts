import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RoomServiceService {

  constructor(private httpClient:HttpClient) { }
  getRooms(){
    return this.httpClient.get('http://127.0.0.1:8080/api/rooms');
  }

  addRooms( data: any){
    return this.httpClient.post('http://127.0.0.1:8080/api/rooms/', data);
  }
  deleteRooms( id:any){
    return this.httpClient.delete('http://127.0.0.1:8080/api/rooms/'+id);
  }


}
