import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-classrooms',
  templateUrl: './classrooms.component.html',
  styleUrls: ['./classrooms.component.sass']
})
export class ClassroomsComponent implements OnInit {
  RoomName="";
  Rooms: string[] = [];
  constructor() { }

  ngOnInit(): void {
  }
  onAdd(){
this.Rooms.push(this.RoomName);
    console.log(this.Rooms);
  }
  onDelete(room:string){
   let index= this.Rooms.findIndex((r) =>{
      return r===room 
    });
    this.Rooms.splice(index,1)
  }

}
