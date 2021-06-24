import { TestBed } from '@angular/core/testing';

import { LastSearchedService } from './last-searched.service';

describe('LastSearchedService', () => {
  let service: LastSearchedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LastSearchedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
