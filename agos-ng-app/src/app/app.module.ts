import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
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
import { FormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ClassroomsComponent,
    NotationComponent,
    SessionsComponent,
    StudentsComponent,
    ProfessorsComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    FormsModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
