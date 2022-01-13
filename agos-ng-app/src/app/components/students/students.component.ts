import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Student } from 'src/app/models/Student';
import { StudentsListService } from 'src/app/services/studentsList-service/students-list.service';


@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.sass']
})
export class StudentsComponent implements OnInit {

  // Students : Student[];
  // Students: Student[];

  displayedColumns: string[] = ['firstName', 'lastName', 'formation'];
  dataSource = new MatTableDataSource<Student>();

  // dataSource = new MatTableDataSource(this.StudentsListService.getStudents());

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }




  constructor(private studentslistservice: StudentsListService) { }

  ngOnInit(): void {
    this.getStudents();

  }
  getStudents() {
    this.studentslistservice.getStudents().subscribe(data => this.dataSource.data = data);
  }

}
