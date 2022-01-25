import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Student } from 'src/app/models/Student';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentsListService {

  constructor(private http:HttpClient) { }

  getStudents(){
    return this.http.get<Student[]>(environment.apiBaseUrl+`students`);
  }
  getStudentsByFormation(formationId : any){
    return this.http.get<Student[]>(environment.apiBaseUrl+`formations/`+formationId+`/students`);
  }

}
