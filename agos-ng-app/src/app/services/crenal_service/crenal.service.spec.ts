import { TestBed } from '@angular/core/testing';

import { CrenalService } from './crenal.service';

describe('CrenalService', () => {
  let service: CrenalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CrenalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
