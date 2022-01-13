import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Department } from 'src/app/models/department';
import { Professor } from 'src/app/models/professor';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfessorsListService {

  constructor(private http:HttpClient) { }

  getProfessors(){
    return this.http.get<Professor[]>(environment.apiBaseUrl+`professors`);
  }
}
