import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth_service/auth.service';

@Component({
  selector: 'app-professor-navbar',
  templateUrl: './professor-navbar.component.html',
  styleUrls: ['./professor-navbar.component.sass']
})
export class ProfessorNavbarComponent implements OnInit {


  panelOpenState = false;

  isAuthenticated : boolean | undefined;
  firstname : string | null;
  lastname : string | null;
  userId : any

  public _reload = true;


  constructor(public authService : AuthService ,private router : Router, private cdr: ChangeDetectorRef) {
    this.firstname =''
    this.lastname =''
  }

  ngOnInit(): void {
    this.userId = localStorage.getItem('user_id')
    console.log(this.userId)
    this.firstname = localStorage.getItem("firstname")
    this.lastname = localStorage.getItem("lastname")
    this.cdr.detectChanges();
    console.log(this.firstname)
  }

  private reload() {
    setTimeout(() => this._reload = false);
    setTimeout(() => this._reload = true);
  }

  logout(){
    this.authService.logout()
    this.isAuthenticated = false
    localStorage.setItem("is_authenticated", String(false))
    this.router.navigateByUrl("login")
    this.reload()
  }

  go(){
		this.router.navigate(['/admin']); // navigate to admin
	}

}
