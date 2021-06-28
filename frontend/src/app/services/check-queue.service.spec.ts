import { TestBed } from '@angular/core/testing';

import { CheckQueueService } from './check-queue.service';

describe('CheckQueueService', () => {
  let service: CheckQueueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckQueueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
