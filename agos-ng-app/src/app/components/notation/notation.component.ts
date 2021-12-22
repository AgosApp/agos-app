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

  criterias = [  
    { title: 'Forme'},  
    { title: 'Mise en avant des principales fonctionnalités '},  
    { title: "Qualité de l'expression orale" },
    { title: 'Justification des méthodes et outils'},  
  ];

  notation_groups = [  
    { title: 'Projets Tuteurés' },  
    { title: 'PFE Informatique' },  
    { title: 'Projets PAM' },
    { title: 'PFE Mécanique' }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
