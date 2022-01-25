import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {COMMA, ENTER, M} from '@angular/cdk/keycodes';
import { ElementRef, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { RoomServiceService } from 'src/app/services/rooms-service/room-service.service';
import { ActivatedRoute, ChildActivationStart } from '@angular/router';
import { Room } from 'src/app/models/room';
import { CrenalService } from 'src/app/services/crenal_service/crenal.service';
import { Crenel } from 'src/app/models/crenel';
import { ThesisService } from 'src/app/services/thesis-service/thesis.service';
import { SessionsService } from 'src/app/services/session_service/sessions.service';
import parse from 'parse-duration'
import { Session } from 'src/app/models/session.model';
import { ProfessorsListService } from 'src/app/services/professorsList-service/professors-list.service';
import { StudentsListService } from 'src/app/services/studentsList-service/students-list.service';
import { Professor } from 'src/app/models/professor';
import { Student } from 'src/app/models/Student';
import { Thesis } from 'src/app/models/thesis';

@Component({
  selector: 'app-crenel',
  templateUrl: './crenel.component.html',
  styleUrls: ['./crenel.component.sass']
})
export class CrenelComponent implements OnInit {
  separatorKeysCodes: number[] = [ENTER, COMMA];
  salleCtrl = new FormControl();
  filteredSalles: Observable<string[]>;
  salles: string[] = [];
  allSalles: string[] = [];
  tmp : any;

  datechangedDebut : any ;
  heureFinChanged : any = 16;
  hoursPossible : any[] = [];
  target : any;
  testsout : any;
  session_id : any;
  crenals : any;
  targetEnd : any;
  thesisByCrenal : any;
  dateCrenal : any;
  timeCrenalFin : any;
  CrenalToSend : Crenel = {
    id: 0, day: "", startTime: "",
    endTime: '',
    session: new Session
  };
 
  SoutenanceToSend : any ={
   title:"",type:"",time:'',finalNote:null,summary:"",
  }
  sallesToSend : any[] = [];
  professors : Professor[] =[];
  students : Student[]=[];
  selectedProfTuteur : any ;
  ProfsTuteurSelected : any[]=[];
  selectedProfTemoin : any ;
  ProfsTemoinSelected : any[]=[];
  selectedStudent : any ;
  StudentsSelected : any[]=[];
  DateSoutenace : any;
  hourdepart : any;
  titleSoutenance : any="" ;
  roomThesis : any;
  crenalThesis : any;
  studentIdToSend : any[]=[];
  professorIdToSend : any[]=[];
  @ViewChild('salleInput') salleInput: ElementRef<HTMLInputElement>;
  
  constructor(private roomservice: RoomServiceService,private crenalservice: CrenalService,
     private route:ActivatedRoute,private thesisservice : ThesisService ,
     private sessionservice : SessionsService , private professorservice : ProfessorsListService  ,
      private studentservice : StudentsListService ) { 
        this.session_id = this.route.snapshot.params['sessionId'];
    this.getSalles();
    this.getCrenal();
    
    
   
  }

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    // Add our fruit
    if (value) {
      this.salles.push(value);
    }
    // Clear the input value
    event.chipInput!.clear();
    this.salleCtrl.setValue(null);
  }

  remove(salle: string): void {
    const index = this.salles.indexOf(salle);

    if (index >= 0) {
      this.salles.splice(index, 1);
    }
  }
  selected(event: MatAutocompleteSelectedEvent): void {
    this.salles.push(event.option.viewValue);
    this.salleInput.nativeElement.value = '';
    this.salleCtrl.setValue(null);
  }
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allSalles.filter(salle => salle.toLowerCase().includes(filterValue));
  }

  getSalles(): void {
      this.roomservice.getRooms().subscribe(res =>{
        this.tmp = res
        this.tmp.map( (t: Room) =>  {
              this.allSalles.push(t.name)
              
        })
        this.filteredSalles = this.salleCtrl.valueChanges.pipe(
          startWith(null),
          map((salle: string | null) => (salle ? this._filter(salle) : this.allSalles.slice())),
        );
      })
      
  }


onAdd() : void{
  console.log(this.crenals[0])
this.CrenalToSend.day = this.dateCrenal;
this.CrenalToSend.startTime = this.datechangedDebut;
this.CrenalToSend.endTime = this.timeCrenalFin;
this.CrenalToSend.session.id = this.crenals[0].session.id;
this.CrenalToSend.session.title = this.crenals[0].session.title;
this.CrenalToSend.session.duration = this.crenals[0].session.duration;
this.CrenalToSend.session.thesisDuration = this.crenals[0].session.thesisDuration;
this.CrenalToSend.session.deliberationDuration = this.crenals[0].session.deliberationDuration;
this.CrenalToSend.session.alertDelay = this.crenals[0].session.alertDelay;
this.CrenalToSend.session.formation_id = this.crenals[0].session.formation.id;
this.CrenalToSend.session.notationGroupId = this.crenals[0].session.notationGroup.id;


this.tmp.map((t: Room) =>{
  this.salles.map((s : any)=>{
    if(t.name == s){
      this.sallesToSend.push(t.id)
    }
  })
})
const sallesToSendString = this.sallesToSend.toString();
this.crenalservice.addCrenal(this.session_id,sallesToSendString,this.CrenalToSend).subscribe(res =>{
  this.getCrenal();
})
}
datechanged($event : any){
  this.dateCrenal = $event.value.getFullYear()+'-'+$event.value.getMonth()+1+'-'+$event.value.getDate();
}
timeChange(){
  this.hoursPossible = []
  this.target = new Date("1970-01-01 "+this.datechangedDebut)
 
  for(let i = 0;i<4;i++){ 
    if((this.target.getMinutes()+parse(this.crenals[0].session.duration,'m')>60)){
      this.target = new Date("1970-01-01 "+(this.target.getHours()+1+":0"+(this.target.getMinutes()-parse(this.crenals[0].session.duration,'m'))));

        this.hoursPossible.push((this.target.getHours()<9?"0"+this.target.getHours():this.target.getHours())+":"+(this.target.getMinutes()<9?"0"+this.target.getMinutes():this.target.getMinutes()))
    }else if((this.target.getMinutes()+parse(this.crenals[0].session.duration,'m')<60)){
      this.target = new Date("1970-01-01 "+(this.target.getHours()+":0"+(this.target.getMinutes()+parse(this.crenals[0].session.duration,'m'))));

      this.hoursPossible.push((this.target.getHours()<9?"0"+this.target.getHours():this.target.getHours())+":"+(this.target.getMinutes()<9?"0"+this.target.getMinutes():this.target.getMinutes()))
    }


  }
console.log(this.hoursPossible)
}
/*heureDebutChanged($event : any){
  console.log($event.value.getHours()+':'+$event.value.getMinutes()+':'+$event.value.getSeconds());
}*/

getCrenal(): void{
this.crenalservice.getCrenals(this.session_id).subscribe(res =>{
  this.crenals = res;
 
  this.crenals.map((crenal : any)=>{
    crenal.hoursPossible=[]
    const tmpDate= new Date(crenal.day)
    crenal.day=tmpDate.toLocaleDateString("fr", { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' })
  this.target = new Date("1970-01-01 "+crenal.startTime)
  this.targetEnd = new Date("1970-01-01 "+crenal.endTime)
  const nbrSout =((this.target.getMinutes()-this.targetEnd.getMinutes())!=0)?(this.targetEnd.getHours()-this.target.getHours())*2 : ((this.targetEnd.getHours()-this.target.getHours())*2)-1
 
  for(let i = 0;i<nbrSout;i++){ 
    console.log(this.target.getMinutes()+parse(crenal.session.duration,'m'))
    if((this.target.getMinutes()+parse(crenal.session.duration,'m')>=60)){
      console.log(this.target.getMinutes())
      this.target = new Date("1970-01-01 "+(this.target.getHours()+1+":0"+(this.target.getMinutes()-parse(crenal.session.duration,'m'))));

      crenal.hoursPossible.push((this.target.getHours()<9?"0"+this.target.getHours():this.target.getHours())+":"+(this.target.getMinutes()<9?"0"+this.target.getMinutes():this.target.getMinutes()))
    }else if((this.target.getMinutes()+parse(crenal.session.duration,'m')<60)){
      this.target = new Date("1970-01-01 "+(this.target.getHours()+":0"+(this.target.getMinutes()+parse(crenal.session.duration,'m'))));

      crenal.hoursPossible.push((this.target.getHours()<9?"0"+this.target.getHours():this.target.getHours())+":"+(this.target.getMinutes()<9?"0"+this.target.getMinutes():this.target.getMinutes()))
    }


  }

  this.getThesisByCrenal(crenal.id)
  })
  
console.log(this.crenals[1].hoursPossible)

})

;
}

getThesisByCrenal(crenalId : any) : void {
  this.thesisservice.getThesisByCrenal(crenalId).subscribe(res =>{
      this.thesisByCrenal = res;
      this.thesisByCrenal.map((thesis : any)=>{
        const tmpTime = new Date(thesis.time);
        thesis.time = tmpTime.getHours()+":"+tmpTime.getMinutes();

      })
      
      console.log(  this.thesisByCrenal)
  })
  
}
select ($event : any) : void{
  this.timeCrenalFin = $event.value
}
getProfessor(departementId : any) : void {
  this.professorservice.getProfessorsByDepartment(departementId).subscribe(res =>{
        this.professors = res
       
  })
}
getStudent(formationId : any) : void {
  this.studentservice.getStudentsByFormation(formationId).subscribe(res =>{
      this.students = res;
  })
}
selectProftemoin () : void {
  this.professors.map((prof : any) =>{
    if(prof.id == this.selectedProfTemoin){
      this.ProfsTemoinSelected.push(prof)
    }
  })

console.log(this.ProfsTemoinSelected)
}
selectProftuteur () : void {
  this.professors.map((prof : any) =>{
    if(prof.id == this.selectedProfTuteur){
      this.ProfsTuteurSelected.push(prof)
      
    }
  })
console.log(this.ProfsTuteurSelected)

}
selectStudent () : void {
  this.students.map((student : any) =>{
    if(student.id == this.selectedStudent){
      this.StudentsSelected.push(student)
      
    }
  })
console.log(this.StudentsSelected)

}
removeProfTemoin(prof : any) : void {
  var myIndex = this.ProfsTemoinSelected.indexOf(prof);
if (myIndex !== -1) {
  this.ProfsTemoinSelected.splice(myIndex, 1);
  this.selectedProfTemoin=""
 
}
}
removeProfTuteur(prof : any) : void {
  var myIndex = this.ProfsTuteurSelected.indexOf(prof);
if (myIndex !== -1) {
  this.ProfsTuteurSelected.splice(myIndex, 1);
  this.selectedProfTuteur =""
}

}
removeStudent(student : any) : void {
  var myIndex = this.StudentsSelected.indexOf(student);
if (myIndex !== -1) {
  this.StudentsSelected.splice(myIndex, 1);
  this.selectedStudent =""
}
}

teleportToform(section : any, salle : any, hour: any , day :any , crenal: any) : void{
    this.DateSoutenace = day;
    this.hourdepart = hour;
    this.roomThesis = salle;
    const days = new Date(crenal.day+" "+this.hourdepart)
   this.crenalThesis = crenal
   this.crenalThesis.day = days.getFullYear()+"-"+days.getMonth()+1+"-"+days.getDate()

    window.location.hash = '';
    window.location.hash = section;

}

addSoutenance() : void {
  
  this.SoutenanceToSend.title=this.titleSoutenance;
  
 
 
  this.StudentsSelected.map((s:any)=>{
    this.studentIdToSend.push(s.id)
  });
  this.professorIdToSend.push(this.ProfsTuteurSelected[0].id)
  this.professorIdToSend.push(this.ProfsTemoinSelected[0].id)
  const time = new Date(this.crenalThesis.day+" "+this.hourdepart)

  this.SoutenanceToSend.time=time.getFullYear()+"-"+time.getMonth()+1+"-"+time.getDate()+" "+(time.getHours()<=9?"0"+time.getHours():time.getHours())+":"+(time.getMinutes()<=9?"0"+time.getMinutes():time.getMinutes());
  console.log(this.SoutenanceToSend)
  
  
 this.thesisservice.addThesis(this.crenalThesis.id,this.roomThesis.id,this.studentIdToSend.toString(),this.professorIdToSend.toString(),this.SoutenanceToSend).subscribe(res =>{
    this.getCrenal() })
}
  ngOnInit(): void {
    
   console.log(this.session_id)
   setTimeout(() => {
     this.getProfessor(4)
   }, 1000);
   setTimeout(() => {
    this.getStudent(3)
  }, 1000);
   
  }

}
