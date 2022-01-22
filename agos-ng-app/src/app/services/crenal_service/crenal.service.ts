import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Room } from 'src/app/models/room';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CrenalService {

  constructor(private httpClient : HttpClient) { }
  
}
