import {Component, OnInit} from '@angular/core';
import {ThesisService} from "../../services/thesis-service/thesis.service";
import {Thesis} from "../../models/thesis";
import { faClock, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-student-theses',
  templateUrl: './student-theses.component.html',
  styleUrls: ['./student-theses.component.sass']
})
export class StudentThesesComponent implements OnInit {

   pastTheses! : Thesis[];
   incomingTheses! : Thesis[];
   theses! : Thesis[]
   faClock = faClock;
   faLocation = faMapMarkerAlt
    countIncoming! : number
    countPast! : number
  userId : any



  constructor(private thesisService: ThesisService) {
  }

  ngOnInit(): void {
    this.getTheses()
    this.userId = localStorage.getItem('user_id')

  }


  getTheses(){
    this.thesisService.getThesesByStudent(localStorage.getItem('user_id')).subscribe(theses =>{

      this.incomingTheses = theses.filter(thesis =>{ // @ts-ignore
        new Date(thesis.time) > Date.now() })

      this.pastTheses = theses.filter(thesis =>{ // @ts-ignore
        new Date(thesis.time) < Date.now() })

      theses.forEach(thesis =>{
        // @ts-ignore
        if(new Date(thesis.time) < Date.now()){
          this.pastTheses?.push(thesis)
        }
         else { // @ts-ignore
          if( new Date(thesis.time) > Date.now()){ // @ts-ignore
                        this.incomingTheses?.push(thesis)
          }
        }
      }
      )
      this.countIncoming = this.incomingTheses.length
      this.countPast = this.pastTheses.length
    })
  }

  thesisInFuture(thesis : Thesis){
    // @ts-ignore
    return new Date(thesis.time) > Date.now()
  }

}
