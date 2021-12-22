import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

export interface Student {
  nom: string;
  prenom: string;
  formation: string;
}


const students: Student[] = [  
  { nom: 'Jhon', prenom:"Doe", formation:"ISL"},  
  { nom: 'Weaver', prenom:"Jayme", formation:"ISL"},  
  { nom: 'Elric', prenom:"Alphonse", formation:"ISL"},  
  { nom: 'Rousseau', prenom:"Theodore", formation:"ISL"},  
  { nom: 'Diedrot', prenom:"Simon", formation:"ISL"},  
  { nom: 'Sartre', prenom:"Jean-Paul", formation:"ISL"},  
  { nom: 'Perez', prenom:"Ricardo", formation:"ISL"},
];

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.sass']
})
export class StudentsComponent implements OnInit {

  displayedColumns: string[] = ['nom', 'prenom', 'formation'];
  dataSource = new MatTableDataSource(students);

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }


  constructor() { }

  ngOnInit(): void {
  }

}
