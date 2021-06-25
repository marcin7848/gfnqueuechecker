import { TestBed } from '@angular/core/testing';

import { ServerGroupService } from './server-group.service';

describe('ServerGroupService', () => {
  let service: ServerGroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServerGroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
