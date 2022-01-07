import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import { ClassroomsComponent } from './components/classrooms/classrooms.component';
import { NotationComponent } from './components/notation/notation.component';
import { ProfessorsComponent } from './components/professors/professors.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { StudentsComponent } from './components/students/students.component';
import { DepartmentComponent } from './components/department/department.component';
import {LoginComponent} from "./components/login/login.component";
import {AuthGuardService as AuthGuard}  from "./services/auth_service/auth-guard.service";
import {RoleGuardService as RoleGuard} from "./services/auth_service/role-guard.service";

const routes: Routes = [
  {path:'', component: LoginComponent},
  {path:'classrooms', component: ClassroomsComponent,canActivate : [AuthGuard, RoleGuard],
    data: {
      expectedRole: 'ADMIN_ROLE'
    }},
  {path:'notation', component: NotationComponent, canActivate : [AuthGuard, RoleGuard],
    data: {
      expectedRole: 'ADMIN_ROLE'
    }},
  {path:'sessions', component: SessionsComponent, canActivate : [AuthGuard, RoleGuard],
    data: {
      expectedRole: 'ADMIN_ROLE'
    }},
  {path:'students', component: StudentsComponent, canActivate : [AuthGuard, RoleGuard],
    data: {
      expectedRole: 'ADMIN_ROLE'
    }},
  {path:'professors', component: ProfessorsComponent, canActivate : [AuthGuard , RoleGuard],
    data: {
      expectedRole: 'ADMIN_ROLE'
    } },
  {path:'department', component: DepartmentComponent,    canActivate : [AuthGuard , RoleGuard],
    data: {
      expectedRole: 'ADMIN_ROLE'
    } },
  {path:'login', component: LoginComponent,},
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
