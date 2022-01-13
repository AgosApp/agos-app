import { TestBed } from '@angular/core/testing';

import { ProfessorsListService } from './professors-list.service';

describe('ProfessorsListService', () => {
  let service: ProfessorsListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfessorsListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
