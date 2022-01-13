import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Department } from 'src/app/models/department';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private httpClient : HttpClient) { }
  getDepartments(){
    return this.httpClient.get<Department[]>(environment.apiBaseUrl+"departments");
  }
  addDepartments( data: any){
    return this.httpClient.post(environment.apiBaseUrl+'departments/', data);
  }
}
