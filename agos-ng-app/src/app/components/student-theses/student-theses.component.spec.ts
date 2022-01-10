import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentThesesComponent } from './student-theses.component';

describe('StudentThesesComponent', () => {
  let component: StudentThesesComponent;
  let fixture: ComponentFixture<StudentThesesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentThesesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentThesesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
