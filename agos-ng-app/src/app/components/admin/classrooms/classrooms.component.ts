import { Component, OnInit } from '@angular/core';
import { RoomServiceService } from 'src/app/services/rooms-service/room-service.service';


@Component({
  selector: 'app-classrooms',
  templateUrl: './classrooms.component.html',
  styleUrls: ['./classrooms.component.sass']
})
export class ClassroomsComponent implements OnInit {
  RoomName="";
  Rooms: any;
  constructor(private roomservice: RoomServiceService) { }

  ngOnInit(): void {
    this.getRooms();
  }
  getRooms(){
    this.roomservice.getRooms().subscribe(res =>{
  
      this.Rooms = res;
    });
  }

  onAdd(){

    this.addRooms();
  }
  addRooms(){
    this.roomservice.addRooms({id:0,name:this.RoomName,description:""}).subscribe(res =>{
      //console.log(res);

      //to display the new formation on card instantly
      this.getRooms();

      //successAlert
      //this.successAlertNotification();
    });
  }
  onDelete(room:any){
  this.roomservice.deleteRooms(room.id).subscribe(res =>{
    //console.log(res);

    //to display the new formation on card instantly
    this.getRooms();

    //successAlert
    //this.successAlertNotification();
  });
  }

}
