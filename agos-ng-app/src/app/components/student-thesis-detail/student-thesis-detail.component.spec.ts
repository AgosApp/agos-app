import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentThesisDetailComponent } from './student-thesis-detail.component';

describe('StudentThesisDetailComponent', () => {
  let component: StudentThesisDetailComponent;
  let fixture: ComponentFixture<StudentThesisDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentThesisDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentThesisDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
