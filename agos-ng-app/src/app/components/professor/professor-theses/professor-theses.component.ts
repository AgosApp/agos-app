import { Component, OnInit } from '@angular/core';
import {Thesis} from "../../../models/thesis";
import {faClock ,faMapMarkerAlt} from "@fortawesome/free-solid-svg-icons";
import {EvaluationService} from "../../../services/evaluatuon-service/evaluation.service";
import {Evaluation} from "../../../models/evaluation";

@Component({
  selector: 'app-professor-theses',
  templateUrl: './professor-theses.component.html',
  styleUrls: ['./professor-theses.component.sass']
})
export class ProfessorThesesComponent implements OnInit {

  pastTheses! : Thesis[];
  incomingTheses! : Thesis[];
  theses! : Thesis[]
  faClock = faClock;
  faLocation = faMapMarkerAlt
  countIncoming! : number
  countPast! : number
  pastEvaluations! : Evaluation[]
  incomingEvaluations! : Evaluation[]


  constructor(private evaluationService : EvaluationService) { }

  ngOnInit(): void {
    this.getTheses()
  }

  getTheses(){

    this.evaluationService.getEvaluationsByProfessor( localStorage.getItem('user_id'))
      .subscribe(
      evaluations => {
        this.incomingEvaluations = evaluations.filter(evaluation =>{ // @ts-ignore
          new Date(evaluation.thesis.time) > Date.now() })

        this.pastEvaluations = evaluations.filter(evaluation =>{ // @ts-ignore
          new Date(evaluation.thesis.time) < Date.now() })

        evaluations.forEach(evaluation =>{
            // @ts-ignore
            if(new Date(evaluation.thesis.time) < Date.now()){
              this.pastEvaluations?.push(evaluation)
            }
            else { // @ts-ignore
              if( new Date(evaluation.thesis.time) > Date.now()){ // @ts-ignore
                this.incomingEvaluations?.push(evaluation)
              }
            }
          }
        )
        this.countIncoming = this.incomingEvaluations.length
        this.countPast = this.pastEvaluations.length
      }




    )
  }

}
