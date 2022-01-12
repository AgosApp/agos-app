import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.sass']
})
export class CreateFormComponent implements OnInit {

  criterias = [  
    { title: 'Forme'},  
    { title: 'Mise en avant des principales fonctionnalités '},  
    { title: "Qualité de l'expression orale" },
    { title: 'Justification des méthodes et outils'},
    { title: 'Justification des méthodes et outils'},
  ];

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
