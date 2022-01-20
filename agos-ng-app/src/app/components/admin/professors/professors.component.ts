import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Professor } from 'src/app/models/professor';
import { ProfessorsListService } from 'src/app/services/professorsList-service/professors-list.service';
import {faCheckCircle} from "@fortawesome/free-solid-svg-icons";



@Component({
  selector: 'app-professors',
  templateUrl: './professors.component.html',
  styleUrls: ['./professors.component.sass']
})
export class ProfessorsComponent implements OnInit {

  faCheck = faCheckCircle;

  displayedColumns: string[] = ['lastName', 'firstName', 'username','admin'];
  dataSource = new MatTableDataSource<Professor>();

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor(private professorslistservice: ProfessorsListService) { }

  ngOnInit(): void {
    this.getProfessors();
  }

  getProfessors() {
    this.professorslistservice.getProfessors().subscribe(data => this.dataSource.data = data);
  }
}
