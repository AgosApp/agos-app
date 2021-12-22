import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';




export interface Professor {
  nom: string;
  prenom: string;
  abr: string;
  is_admin: boolean;
}


const professors: Professor[] = [  
  { nom: 'Jounaid', prenom:'Youssef',abr:'YJO', is_admin: false},
  { nom: 'Boufelja', prenom:'Mourtada',abr:'MBF', is_admin: false},
  { nom: 'Sbaibi', prenom:'Nossair',abr:'NSB', is_admin: true},
  { nom: 'Boutahir', prenom:'Mounia',abr:'MBT', is_admin: false},
];


@Component({
  selector: 'app-professors',
  templateUrl: './professors.component.html',
  styleUrls: ['./professors.component.sass']
})
export class ProfessorsComponent implements OnInit {

  professors = [

  ];

  displayedColumns: string[] = ['nom', 'prenom', 'abv','is_admin'];
  dataSource = new MatTableDataSource(professors);

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
