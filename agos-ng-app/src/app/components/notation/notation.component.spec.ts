import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotationComponent } from './notation.component';

describe('NotationComponent', () => {
  let component: NotationComponent;
  let fixture: ComponentFixture<NotationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
