import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClassroomsComponent } from './classrooms/classrooms.component';
import { NotationComponent } from './notation/notation.component';
import { ProfessorsComponent } from './professors/professors.component';
import { SessionsComponent } from './sessions/sessions.component';
import { StudentsComponent } from './students/students.component';

const routes: Routes = [
  {path:'classrooms', component: ClassroomsComponent},
  {path:'notation', component: NotationComponent},
  {path:'sessions', component: SessionsComponent},
  {path:'students', component: StudentsComponent},
  {path:'professors', component: ProfessorsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
