import { Component, OnInit } from '@angular/core';
import {EvaluationService} from "../../services/evaluatuon-service/evaluation.service";
import {Evaluation} from "../../models/evaluation";
import {ActivatedRoute} from "@angular/router";
import {ThesisService} from "../../services/thesis-service/thesis.service";
import {Thesis} from "../../models/thesis";
import {faClock, faMapMarkerAlt} from "@fortawesome/free-solid-svg-icons";
import {CriteriaEvaluationService} from "../../services/criteria-evaluation-service/criteria-evaluation.service";
import {CriteriaEvaluation} from "../../models/criteriaEvaluation";

@Component({
  selector: 'app-student-thesis-detail',
  templateUrl: './student-thesis-detail.component.html',
  styleUrls: ['./student-thesis-detail.component.sass']
})
export class StudentThesisDetailComponent implements OnInit {

  evaluations!: Evaluation[];
  thesis! : Thesis
  criteriaEvaluations! : CriteriaEvaluation[]
  faClock = faClock;
  faLocation = faMapMarkerAlt
  critters: string[] = []
  criteriaEvaluationsNotes! : number[]
  noteFinale! : number;
  notes : any[]=[]
  thesisNoted : boolean = false;

  constructor(
              private thesisService: ThesisService,
              private evaluationService: EvaluationService,
              private criteriaEvaluationService : CriteriaEvaluationService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getThesis()
  }

  getThesis(){
    let thesisId = this.activatedRoute.snapshot.params['thesisId'];
    this.evaluationService.getEvaluationsByThesis(thesisId).subscribe(
      evaluations => {
        this.evaluations = evaluations
      }
    )

    let userId = localStorage.getItem('user_id')
    this.thesisService.getThesisByStudent(userId, thesisId).subscribe(
      thesis => {
        this.thesis = thesis
      }
    )

    this.criteriaEvaluationService.getCriteriaEvaluationsByThesis(thesisId).subscribe(
      criteriaEvalutions => {
        this.criteriaEvaluations = criteriaEvalutions

        //filter criterias become they come dublicated from backend
        let duplicatesCriteria: string[] = []
        this.criteriaEvaluations.forEach(ce =>duplicatesCriteria.push(ce.criteria.title))
        this.critters = [...new Set(duplicatesCriteria)]

        //get marks for each criteria
        let duplicataCriteriaEvaluations : number[] = []
        this.criteriaEvaluations.forEach(ce =>duplicataCriteriaEvaluations.push(ce.note))
        this.criteriaEvaluationsNotes = [...new Set(duplicataCriteriaEvaluations)]


        //calculate the final mark
        this.criteriaEvaluations.forEach(ce => {
          if(ce.note != null) this.thesisNoted = true
          this.notes.push(ce.note)})
        this.noteFinale = this.notes.reduce(function(a, b) { return a + b; }, 0)/this.notes.length

      }
    )
  }

}
