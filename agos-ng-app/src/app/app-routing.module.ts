import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClassroomsComponent } from './components/classrooms/classrooms.component';
import { NotationComponent } from './components/notation/notation.component';
import { ProfessorsComponent } from './components/professors/professors.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { StudentsComponent } from './components/students/students.component';


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
