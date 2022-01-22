import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.sass']
})
export class ProfessorComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit(): void {
  }

  go(){
		this.route.navigate(['/admin']); // navigate to other page
	}

}
