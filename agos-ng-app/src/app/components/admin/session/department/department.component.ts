import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Department } from 'src/app/models/department';
import { DepartmentService } from 'src/app/services/department-service/department.service';

interface IModal {
  name: string;
  description: string;
}
@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.sass']
})



export class DepartmentComponent implements OnInit {

  DepartmentName="";
  DepartmentDescription="";
  Departments! : Department[];
  constructor(private departmentService : DepartmentService,private router : Router) { }

  ngOnInit(): void {
    this.getDepartments();
  }
  getDepartments(){
    this.departmentService.getDepartments().subscribe(res =>{
  
      this.Departments = res;
    });
  }
  onAdd(){
   var model : IModal= {name:this.DepartmentName,description:this.DepartmentDescription};
    this.departmentService.addDepartments(model).subscribe(res =>{
      this.getDepartments()
    })
  }
 /* onDelete(room:object){
   let index= this.Departments.findIndex((r) =>{
      return r===room 
    });
    this.Departments.splice(index,1)
  }*/

}
