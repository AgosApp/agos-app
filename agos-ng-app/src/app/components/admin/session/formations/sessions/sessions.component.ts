import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormationsService} from "../../../../../services/formations.service";
import {SessionsService} from "../../../../../services/session_service/sessions.service";

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.sass']
})
export class SessionsComponent implements OnInit {

  constructor(private route:ActivatedRoute, private formationService:FormationsService, private sessionsService:SessionsService) { }

  ngOnInit(): void {
    this.getSessionsByFormation();
  }

  @Input() formation_id : any;
  //formation_id:any;
  sessions:any;
  getSessionsByFormation(){


    //let formation_id = (document.getElementsByName("formation_id")  );

    console.log('formationnn is'+this.formation_id);
    this.sessionsService.getSessionsByFormation(this.formation_id).subscribe(res =>{
      //console.log('i am in component depart id is'+this.department_id);

      //console.log('formation id is'+this.formation.id);
      this.sessions = res;
    });
  }

}
