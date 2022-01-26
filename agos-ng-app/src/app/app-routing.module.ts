import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import { ClassroomsComponent } from './components/admin/classrooms/classrooms.component';
import { NotationComponent } from './components/admin/notation/notation.component';
import { ProfessorsComponent } from './components/admin/professors/professors.component';
import { SessionsComponent } from './components/admin/session/formations/sessions/sessions.component';
import { StudentsComponent } from './components/admin/students/students.component';

import { FormationsComponent } from './components/admin/session/formations/formations.component';

import { DepartmentComponent } from './components/admin/session/department/department.component';
import {LoginComponent} from "./components/login/login.component";
import {AuthGuardService as AuthGuard}  from "./services/auth_service/auth-guard.service";
import {RoleGuardService as RoleGuard} from "./services/auth_service/role-guard.service";
import {StudentThesesComponent} from "./components/student/student-theses/student-theses.component";
// @ts-ignore
import {ProfessorThesesComponent} from "./components/professor/professor-theses/professor-theses.component";
import {StudentThesisDetailComponent} from "./components/student/student-thesis-detail/student-thesis-detail.component";
import {ProfessorThesisDetailComponent} from "./components/professor/professor-thesis-detail/professor-thesis-detail.component";
import { EvaluationComponent } from './components/evaluation/evaluation.component';
import { StudentComponent } from './components/student/student.component';
import { ProfessorComponent } from './components/professor/professor.component';
import { AdminComponent } from './components/admin/admin.component';
import { SessionComponent } from './components/admin/session/session.component';
import { CrenelComponent } from './components/crenel/crenel.component';


const routes: Routes = [
  {path:'', component: LoginComponent},

  {path:'admin', component: AdminComponent,children: [
     //{ path: '', component:  DepartmentComponent },
    { path: 'classrooms', component:  ClassroomsComponent,pathMatch: 'full' },
    { path: 'notation', component:  NotationComponent,pathMatch: 'full' },
    /*{ path: 'session', component:  SessionComponent,pathMatch: 'full',children:[
      { path: 'departments', component:  DepartmentComponent,pathMatch: 'full'},
      { path: ':departmentId/formations', component:  FormationsComponent,pathMatch: 'full' }
    ]}
      ,*/
      { path: 'session', component:  DepartmentComponent,pathMatch: 'full' },
      { path: 'session/:departmentId/formations', component:  FormationsComponent,pathMatch: 'full' },

      { path: 'students', component:  StudentsComponent,pathMatch: 'full' },
      { path: ':sessionId/crenal', component:  CrenelComponent,pathMatch: 'full' },
    { path: 'professors', component:  ProfessorsComponent,pathMatch: 'full' },
    ], canActivate : [AuthGuard , RoleGuard],
    data: {
      expectedRole: 'ADMIN_ROLE'
    } },


  // {path:'classrooms', component: ClassroomsComponent,canActivate : [AuthGuard, RoleGuard],
  //   data: {
  //     expectedRole: 'ADMIN_ROLE'
  //   }},
  // {path:'notation', component: NotationComponent, canActivate : [AuthGuard, RoleGuard],
  //   data: {
  //     expectedRole: 'ADMIN_ROLE'
  //   }},

  // {path:'sessions', component: DepartmentComponent, canActivate : [AuthGuard, RoleGuard],
  //   data: {
  //     expectedRole: 'ADMIN_ROLE'
  //   }},

  // {path:'students', component: StudentsComponent, canActivate : [AuthGuard, RoleGuard],
  //   data: {
  //     expectedRole: 'ADMIN_ROLE'
  //   }},
  // {path:'professors', component: ProfessorsComponent, canActivate : [AuthGuard , RoleGuard],
  //   data: {
  //     expectedRole: 'ADMIN_ROLE'
  //   } },
  // {path:'departments', component: DepartmentComponent, canActivate : [AuthGuard , RoleGuard],
  //   data: {
  //     expectedRole: 'ADMIN_ROLE'
  //   } },

  //   {path:'departments/:department_id/formations', component: FormationsComponent, canActivate : [AuthGuard, RoleGuard],
  //   data: {
  //     expectedRole: 'ADMIN_ROLE'
  //   }},



// ------------------
// { path: 'dashboard', component: DashboardComponent,
//    children: [
  // { path: '', component:  AComponent },
  // { path: 'componentA', component:  AComponent },
  // { path: 'componentB', component: BComponent },
  // { path: 'componentC', component: CComponent },
  // { path: '', redirectTo: 'componentA', pathMatch: 'full'}
  // ]},



  {path:'students/:userId/theses', component: StudentComponent,children: [
    // { path: '', component:  AComponent },
    { path: ':thesisId', component:  StudentThesisDetailComponent,pathMatch: 'full' },
    { path: '', component: StudentThesesComponent,pathMatch: 'full' },
    ], canActivate : [AuthGuard , RoleGuard],
    data: {
      expectedRole: 'STUDENT_ROLE'
    } },

    {path:'professors/:userId/theses', component: ProfessorComponent,children: [
      // { path: '', component:  AComponent },
      { path: ':thesisId', component:  ProfessorThesisDetailComponent,pathMatch: 'full' },
      { path: '', component: ProfessorThesesComponent,pathMatch: 'full' },
      ], canActivate : [AuthGuard , RoleGuard],
      data: {
        expectedRole: 'PROF_ROLE'
      } },



  // {path:'professors/:userId/theses', component: ProfessorThesesComponent, canActivate : [AuthGuard , RoleGuard],
  //   data: {
  //     expectedRole: 'PROF_ROLE'
  //   } },
  // {path:'professors/:userId/theses/:thesisId', component: ProfessorThesisDetailComponent, canActivate : [AuthGuard , RoleGuard],
  //   data: {
  //     expectedRole: 'PROF_ROLE'
  //   } },

  {path:'login', component: LoginComponent,},
  //{ path : '**', component : FormationsComponent}
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
