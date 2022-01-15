import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormationsService} from "../../services/formations.service";
import {Formation} from "../../models/formation.model";
import {ActivatedRoute} from "@angular/router";
import {SessionsService} from "../../services/session_service/sessions.service";
import {NotationGroup} from "../../models/notation-group.model";
import {Session} from "../../models/session.model";

@Component({
  selector: 'app-formations',
  templateUrl: './formations.component.html',
  styleUrls: ['./formations.component.sass']
})
export class FormationsComponent implements OnInit {


  Formations: string[] = [];
  forms:any;
  department_id:any;
  sessions:any;
  format:any;
  formation_id:any;
  groups:any;
  formation = new Formation();
  session = new Session();

  constructor(private route:ActivatedRoute, private formationService:FormationsService, private sessionsService:SessionsService) { }

  ngOnInit(): void {

    this.department_id = this.route.snapshot.params['department_id'];
    this.getFormations();
    this.getNotationGroups();

  }

  getFormations(){
    this.formationService.getFormations(this.department_id).subscribe(res =>{

      this.forms = res;

    });
  }

  addFormation(){
    this.formationService.addFormation(this.department_id, this.formation).subscribe(res =>{
      //console.log(res);

      //to display the new formation on card instantly
      this.getFormations();

      //successAlert
      //this.successAlertNotification();
    });
  }

  deleteFormation(id:any) {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer cette formation ?')) {
      this.formationService.deleteFormation(this.department_id, id).subscribe(res => {
        this.getFormations();
      });
    }
  }

  getNotationGroups(){
    this.formationService.getNotationGroups().subscribe(res => {

      this.groups = res;
      console.log(this.groups);
    });
  }

  addSession(){
    this.formationService.addSession(this.session.formation_id,this.session.notationGroupId, this.session).subscribe(res =>{
      console.log("i am in add session"+res);

      //to display the new formation on card instantly
      this.getNotationGroups();
    console.log("session is : "+this.session);
      //successAlert
      //this.successAlertNotification();
    });
  }


}
