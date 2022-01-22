import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { ElementRef, ViewChild} from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { RoomServiceService } from 'src/app/services/rooms-service/room-service.service';
import { ChildActivationStart } from '@angular/router';
import { Room } from 'src/app/models/room';
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
  salleTosend : Room[] = [] ;
  datechangedDebut : any ;
  heureFinChanged : any = 16;
  hoursPossible : any[] = [];
  @ViewChild('salleInput') salleInput: ElementRef<HTMLInputElement>;
  constructor(private roomservice: RoomServiceService) { 
    this.getSalles();
   
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
 /*this.tmp.map((t : Room) =>{
   this.salles.map((salle : string) => {
     if(t.name == salle){
       this.salleTosend.push(t)
     }
   })
 })*/
 
}
datechanged($event : any){
  console.log($event.value.getFullYear()+'/'+$event.value.getMonth()+1+'/'+$event.value.getDate());
}
timeChange($event : any){
  const target = new Date("1970-01-01 "+this.datechangedDebut)
  console.log(target.getHours()+1)
}
/*heureDebutChanged($event : any){
  console.log($event.value.getHours()+':'+$event.value.getMinutes()+':'+$event.value.getSeconds());
}*/

  ngOnInit(): void {
    
    
  }

}
