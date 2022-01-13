import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { NotationGroup } from 'src/app/models/notationGroup';
import { NotationService } from 'src/app/services/notation-service/notation.service';
import {Criteria} from 'src/app/models/criteria'

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.sass']
})
export class CreateFormComponent implements OnInit {

  Criterias: any;
  Criteria :Criteria;

  createNotation(): FormGroup {
    return this.formBuilder.group({
      id:0,
      criteria: this.Criteria,
      bareme: 0,
    });
  }

  addNotation(): void {
    this.notations = this.orderForm.get('notations') as FormArray;
    this.notations.push(this.createNotation());
  }

  notations: FormArray;
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
    return (this.orderForm.get('notations') as FormArray).controls;
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
    this.notations.removeAt(i);
  }
   
  // onSubmit() {
  //   console.log(this.productForm.value);
  // }


  getCriterias() {
    this.notationService.getCriterias().subscribe(res =>{

      this.Criterias = res;
    });  }

onCreateGroup(group: NotationGroup){
      this.notationService.createGroup(group).subscribe(() =>{
      });
      }

  ngOnInit() {
    this.getCriterias();
    this.orderForm = this.formBuilder.group({
      notationGroupTitle: '',
      notations: this.formBuilder.array([ this.createNotation() ])
    });
  }

  onSubmit() {
    console.log(this.orderForm.value);
  }


}
