import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ClassroomsComponent } from './components/classrooms/classrooms.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotationComponent } from './components/notation/notation.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { StudentsComponent } from './components/students/students.component';
import { ProfessorsComponent } from './components/professors/professors.component';
import { FooterComponent } from './components/footer/footer.component';
import { DepartmentComponent } from './components/department/department.component';
import { FormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormationsComponent } from './components/formations/formations.component';
import { HttpClientModule } from '@angular/common/http';
import {RouterModule, Routes} from "@angular/router";

/*const appRoutes: Routes = [
{path: 'department/:id/formations', component:FormationsComponent},
 {path: 'department', component:DepartmentComponent},
];*/

@NgModule({
 declarations: [
   AppComponent,
   NavbarComponent,
   ClassroomsComponent,
   NotationComponent,
   SessionsComponent,
   StudentsComponent,
   ProfessorsComponent,
   FooterComponent,
   FormationsComponent,
   DepartmentComponent,
 ],
 imports: [
   BrowserModule,
   AppRoutingModule,
   BrowserAnimationsModule,
   MatToolbarModule,
   MatButtonModule,
   MatGridListModule,
   FormsModule,
   MatIconModule,
   MatFormFieldModule,
   MatInputModule,
   HttpClientModule,
   //RouterModule.forRoot(appRoutes),

],
 providers: [],
 bootstrap: [AppComponent]
})
export class AppModule { }
