import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorThesesComponent } from './professor-theses.component';

describe('ProfessorThesesComponent', () => {
  let component: ProfessorThesesComponent;
  let fixture: ComponentFixture<ProfessorThesesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessorThesesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorThesesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
