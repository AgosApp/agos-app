import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorThesisDetailComponent } from './professor-thesis-detail.component';

describe('ProfessorThesisDetailComponent', () => {
  let component: ProfessorThesisDetailComponent;
  let fixture: ComponentFixture<ProfessorThesisDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorThesisDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorThesisDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
