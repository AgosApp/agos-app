import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';

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

  currentItem = 0;

  criterias = [  
    { title: 'Forme'},  
    { title: 'Mise en avant des principales fonctionnalités '},  
    { title: "Qualité de l'expression orale" },
    { title: 'Justification des méthodes et outils'},
    { title: 'Justification des méthodes et outils'},
  ];

  notation_groups = [  
    { id: 1,title: 'Projets Tuteurés'},  
    { id: 2,title: 'PFE Informatique' },
    { id: 3,title: 'Projets PAM' },
    { id: 4,title: 'PFE Mécanique' },
    { id: 5,title: 'PFE IA' },
    { id: 6,title: 'PFE IA' },
    { id: 7, title: 'PFE IA' },
  ];

  CreateShowing = true;
  EditShowing = false;

toggleEditShow(id: number) {

this.CreateShowing = false;
this.EditShowing = true;
this.currentItem= id;

}
toggleCreateShow() {

  this.CreateShowing = true;
  this.EditShowing = false;
  
  }

  createNotation(): FormGroup {
    return this.formBuilder.group({
      critere: '',
      bareme: '',
    });
  }

  addNotation(): void {
    this.items = this.orderForm.get('items') as FormArray;
    this.items.push(this.createNotation());
  }

  items: FormArray;
  orderForm: FormGroup;

  options: FormGroup;
  hideRequiredControl = new FormControl(false);
  floatLabelControl = new FormControl('auto');

  constructor(private formBuilder: FormBuilder,fb: FormBuilder) {
    this.options = fb.group({
      hideRequired: this.hideRequiredControl,
      floatLabel: this.floatLabelControl,
    });
  }

  get itemsArrayControl() {
    return (this.orderForm.get('items') as FormArray).controls;
  }
  
  // notations() : FormArray {
  //   return this.productForm.get("notations") as FormArray
  // }
   
  // newQuantity(): FormGroup {
  //   return this.fb.group({
  //     critere: '',
  //     bareme: '',
  //   })
  // }
   
  // addQuantity() {
  //   this.notations().push(this.newQuantity());
  // }
   
  removeNotation(i:number) {
    this.items.removeAt(i);
  }
   
  // onSubmit() {
  //   console.log(this.productForm.value);
  // }


  ngOnInit() {
    this.orderForm = this.formBuilder.group({
      name: '',
      items: this.formBuilder.array([ this.createNotation() ])
    });
  }

  onSubmit() {
    console.log(this.orderForm.value);
  }



}


