import { TestBed } from '@angular/core/testing';

import { NotationService } from './notation.service';

describe('NotationService', () => {
  let service: NotationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
