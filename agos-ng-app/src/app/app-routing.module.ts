import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClassroomsComponent } from './components/classrooms/classrooms.component';
import { NotationComponent } from './components/notation/notation.component';
import { ProfessorsComponent } from './components/professors/professors.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { StudentsComponent } from './components/students/students.component';
import { DepartmentComponent } from './components/department/department.component';
import {LoginComponent} from "./components/login/login.component";
import {AuthGuardService} from "./services/auth_service/auth-guard.service";

const routes: Routes = [
  {path:'', component: LoginComponent},
  {path:'classrooms', component: ClassroomsComponent,/* canActivate : [AuthGuardService]*/},
  {path:'notation', component: NotationComponent,/* canActivate : [AuthGuardService]*/},
  {path:'sessions', component: SessionsComponent,/* canActivate : [AuthGuardService]*/},
  {path:'students', component: StudentsComponent,/* canActivate : [AuthGuardService]*/},
  {path:'professors', component: ProfessorsComponent,/* canActivate : [AuthGuardService], data : {role : "ADMIN_ROLE"}*/},
  {path:'department', component: DepartmentComponent,/* canActivate : [AuthGuardService]*/},
  {path:'login', component: LoginComponent},
 // { path: '**', component: LoginComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
