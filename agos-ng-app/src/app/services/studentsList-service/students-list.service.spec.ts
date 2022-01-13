import { TestBed } from '@angular/core/testing';

import { StudentsListService } from './students-list.service';

describe('StudentsListService', () => {
  let service: StudentsListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentsListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
