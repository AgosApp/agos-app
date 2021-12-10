import { Component, OnInit } from '@angular/core';

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

@Component({
  selector: 'app-notation',
  templateUrl: './notation.component.html',
  styleUrls: ['./notation.component.sass']
})
export class NotationComponent implements OnInit {

  cards = [  
    { title: 'Critères', cols: 1, rows: 1 },  
    { title: 'Créer Groupe de Notation', cols: 1, rows: 2 },  
    { title: 'Groupes de Notation', cols: 1, rows: 1 }
  ]; 

  constructor() { }

  ngOnInit(): void {
  }

}
