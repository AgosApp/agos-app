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
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { LoginComponent } from './components/login/login.component';
import {MatCard, MatCardModule} from "@angular/material/card";
import {MatDialogModule} from "@angular/material/dialog";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {AuthorizationInterceptor} from "./components/auth.interceptor";
import {AuthGuardService} from "./services/auth_service/auth-guard.service";
import {JWT_OPTIONS, JwtHelperService} from "@auth0/angular-jwt";
import {RoleGuardService} from "./services/auth_service/role-guard.service";
import { StudentThesesComponent } from './components/student-theses/student-theses.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";

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
    DepartmentComponent,
    LoginComponent,
    StudentThesesComponent
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
    MatCardModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSnackBarModule,
    HttpClientModule,
    BrowserModule,
    FontAwesomeModule
  ],
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
  bootstrap: [AppComponent]
})
export class AppModule { }
