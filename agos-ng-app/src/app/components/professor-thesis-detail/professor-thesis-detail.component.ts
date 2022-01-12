import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ThesisService} from "../../services/thesis-service/thesis.service";
import {Thesis} from "../../models/thesis";
import {faPlayCircle, faClock, faMapMarkerAlt} from "@fortawesome/free-solid-svg-icons";
import {Evaluation} from "../../models/evaluation";
import {EvaluationService} from "../../services/evaluatuon-service/evaluation.service";

@Component({
  selector: 'app-professor-thesis-detail',
  templateUrl: './professor-thesis-detail.component.html',
  styleUrls: ['./professor-thesis-detail.component.sass']
})
export class ProfessorThesisDetailComponent implements OnInit {

  thesis!: Thesis
  evaluations!: Evaluation[];
  faClock = faClock;
  faLocation = faMapMarkerAlt
  faPlayCircle= faPlayCircle

  constructor(private activatedRoute: ActivatedRoute,
              private thesisService : ThesisService,
              private evaluationService: EvaluationService
              ) { }

  ngOnInit(): void {
    this.getThesis()
  }

  getThesis() {
    let userId = localStorage.getItem('user_id')
    let thesisId = this.activatedRoute.snapshot.params['thesisId'];

    this.thesisService.getThesisByProfessor(userId, thesisId).subscribe(
      thesis => {
        this.thesis = thesis
      }
    )

    this.evaluationService.getEvaluationsByThesis(thesisId).subscribe(
      evaluations => {
        this.evaluations = evaluations
      }
    )
  }
}
