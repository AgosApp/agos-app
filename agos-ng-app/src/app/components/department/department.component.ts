import { Component, OnInit } from '@angular/core';

interface IModal {
  nom: string;
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
  Departments :IModal[]=[];
  constructor() { }

  ngOnInit(): void {
  }
  onAdd(){
   var model : IModal= {nom:this.DepartmentName,description:this.DepartmentDescription};
    this.Departments.push(model);
    console.log(this.Departments[1].nom);
  }
  onDelete(room:object){
   let index= this.Departments.findIndex((r) =>{
      return r===room 
    });
    this.Departments.splice(index,1)
  }

}
