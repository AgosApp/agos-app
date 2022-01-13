import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Criteria } from 'src/app/models/criteria';
import { NotationGroup } from 'src/app/models/notationGroup';
import { NotationService } from 'src/app/services/notation-service/notation.service';

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

  Groups: any;
  Criterias: any;

  currentItem = 0;
  CriteriaName="";

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

  constructor(private formBuilder: FormBuilder,fb: FormBuilder,private notationService:NotationService) {
    this.options = fb.group({
      hideRequired: this.hideRequiredControl,
      floatLabel: this.floatLabelControl,
    });
  }

  get itemsArrayControl() {
    return (this.orderForm.get('items') as FormArray).controls;
  }
  
  removeNotation(i:number) {
    this.items.removeAt(i);
  }
   

  getNotationGroups(){
    this.notationService.getNotationGroups().subscribe(res =>{

      this.Groups = res;
    });
  }
  getCriterias() {
    this.notationService.getCriterias().subscribe(res =>{

      this.Criterias = res;
    });  }

  ngOnInit() {
    this.getNotationGroups();
    this.getCriterias();
    this.orderForm = this.formBuilder.group({
      name: '',
      items: this.formBuilder.array([ this.createNotation() ])
    });
  }

  onAddCriteria(){
        this.addCriteria();
      }

      addCriteria(){
        this.notationService.addCriteria({id:0,title:this.CriteriaName}).subscribe(() =>{

          this.getCriterias();

        });
      }    

      onDeleteCriteria(criteria: Criteria){
        this.notationService.deleteCriteria(criteria.id).subscribe(() =>{

          this.getCriterias();

        });
        }


      

  onSubmit() {
    console.log(this.orderForm.value);
  }



}


