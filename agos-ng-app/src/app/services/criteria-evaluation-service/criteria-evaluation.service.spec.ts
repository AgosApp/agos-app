import { TestBed } from '@angular/core/testing';

import { CriteriaEvaluationService } from './criteria-evaluation.service';

describe('CriteriaEvaluationService', () => {
  let service: CriteriaEvaluationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CriteriaEvaluationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
