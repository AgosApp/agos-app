import { Component, OnInit } from '@angular/core';
import {FormationsService} from "../../services/formations.service";
import {Formation} from "../../models/formation.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-formations',
  templateUrl: './formations.component.html',
  styleUrls: ['./formations.component.sass']
})
export class FormationsComponent implements OnInit {

  FormationName="";
  Formations: string[] = [];
  forms:any;
  department_id:any;
  constructor(private route:ActivatedRoute, private formationService:FormationsService) { }

  ngOnInit(): void {

    this.department_id = this.route.snapshot.params['department_id'];
    //console.log(this.department_id);
    this.getFormations();

  }

  getFormations(){
    this.formationService.getFormations(this.department_id).subscribe(res =>{
      //console.log('i am in component depart id is'+this.department_id);

      //console.log('res is'+res);
      this.forms = res;
    });
  }

  onAdd(){
    this.Formations.push(this.FormationName);
    //console.log(this.Formations);
  }

  onDelete(formation:string){
    let index= this.Formations.findIndex((f) =>{
      return f===formation
    });
    this.Formations.splice(index,1)
  }

  formation = new Formation();
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
}
