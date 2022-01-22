import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrenelComponent } from './crenel.component';

describe('CrenelComponent', () => {
  let component: CrenelComponent;
  let fixture: ComponentFixture<CrenelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrenelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CrenelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
