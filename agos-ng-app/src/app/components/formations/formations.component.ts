import { Component, OnInit } from '@angular/core';
import {FormationsService} from "../../services/formations.service";
import {Formation} from "../../models/formation.model";

@Component({
  selector: 'app-formations',
  templateUrl: './formations.component.html',
  styleUrls: ['./formations.component.sass']
})
export class FormationsComponent implements OnInit {

  FormationName="";
  Formations: string[] = [];
  forms:any;
  constructor(private formationService:FormationsService) { }

  ngOnInit(): void {
    this.getFormations();
  }

  getFormations(){

    this.formationService.getFormations().subscribe(res =>{
      //console.log(res);
      this.forms = res;
    });
  }

  onAdd(){
    this.Formations.push(this.FormationName);
    console.log(this.Formations);
  }

  onDelete(formation:string){
    let index= this.Formations.findIndex((f) =>{
      return f===formation
    });
    this.Formations.splice(index,1)
  }

  formation = new Formation();
  addFormation(){
    this.formationService.addFormation(this.formation).subscribe(res =>{
      console.log(res);

      //to display the new formation on card instantly
      this.getFormations();

      //successAlert
      //this.successAlertNotification();
    });
  }

  deleteFormation(id:any) {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer cette formation ?')) {
      this.formationService.deleteFormation(id).subscribe(res => {
        this.getFormations();
      });
    }
  }
}
