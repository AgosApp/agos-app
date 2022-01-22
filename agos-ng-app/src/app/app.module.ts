import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {ReactiveFormsModule } from '@angular/forms';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatSelectModule} from '@angular/material/select';
import {MatPaginatorModule} from '@angular/material/paginator';
import { FormsModule } from '@angular/forms';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ClassroomsComponent } from './components/admin/classrooms/classrooms.component';
import { NavbarComponent } from './components/admin/navbar/navbar.component';
import { NotationComponent } from './components/admin/notation/notation.component';
import { SessionsComponent } from './components/admin/session/formations/sessions/sessions.component';
import { StudentsComponent } from './components/admin/students/students.component';
import { ProfessorsComponent } from './components/admin/professors/professors.component';
import { FooterComponent } from './components/footer/footer.component';
import { EditFormComponent } from './components/admin/notation/edit-form/edit-form.component';
import { CreateFormComponent } from './components/admin/notation/create-form/create-form.component';
import { DepartmentComponent } from './components/admin/session/department/department.component';
import {MatSliderModule} from '@angular/material/slider';
import { FormationsComponent } from './components/admin/session/formations/formations.component';
import {RouterModule, Routes} from "@angular/router";
import { LoginComponent } from './components/login/login.component';
import {MatCard, MatCardModule} from "@angular/material/card";
import {MatDialogModule} from "@angular/material/dialog";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {AuthorizationInterceptor} from "./components/auth.interceptor";
import {AuthGuardService} from "./services/auth_service/auth-guard.service";
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {RoleGuardService} from "./services/auth_service/role-guard.service";
import { StudentThesesComponent } from './components/student/student-theses/student-theses.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import { ProfessorThesesComponent } from './components/professor/professor-theses/professor-theses.component';
import { StudentThesisDetailComponent } from './components/student/student-thesis-detail/student-thesis-detail.component';
import { ProfessorThesisDetailComponent } from './components/professor/professor-thesis-detail/professor-thesis-detail.component';
import { EvaluationComponent } from './components/evaluation/evaluation.component';
import { EvaluationBodyComponent } from './components/evaluation/evaluation-body/evaluation-body.component';
import { EvaluationNavbarComponent } from './components/evaluation/evaluation-navbar/evaluation-navbar.component';
import { AdminComponent } from './components/admin/admin.component';
import { StudentComponent } from './components/student/student.component';
import { ProfessorComponent } from './components/professor/professor.component';
import { StudentNavbarComponent } from './components/student/student-navbar/student-navbar.component';
import { ProfessorNavbarComponent } from './components/professor/professor-navbar/professor-navbar.component';
import { SessionComponent } from './components/admin/session/session.component';

// @ts-ignore
// @ts-ignore
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
    EditFormComponent,
    CreateFormComponent,
    DepartmentComponent,
    LoginComponent,
    StudentThesesComponent,
    ProfessorThesesComponent,
    StudentThesisDetailComponent,
    ProfessorThesisDetailComponent,
    FormationsComponent,
    EvaluationComponent,
    EvaluationBodyComponent,
    EvaluationNavbarComponent,
    AdminComponent,
    StudentComponent,
    ProfessorComponent,
    StudentNavbarComponent,
    ProfessorNavbarComponent,
    SessionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatCardModule,
    MatGridListModule,
    MatMenuModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    FormsModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatGridListModule,
    FormsModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSnackBarModule,
    HttpClientModule,
    FontAwesomeModule,
    MatExpansionModule,
    MatSliderModule
  ],
  bootstrap: [AppComponent],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthorizationInterceptor,
    multi: true
  },
    {
      provide: AuthGuardService,
    },
    {
      provide: RoleGuardService,
    },
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService],

})
export class AppModule { }
