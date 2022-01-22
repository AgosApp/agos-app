import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluationBodyComponent } from './evaluation-body.component';

describe('EvaluationBodyComponent', () => {
  let component: EvaluationBodyComponent;
  let fixture: ComponentFixture<EvaluationBodyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvaluationBodyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EvaluationBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
