import { TestBed } from '@angular/core/testing';

import { SearchPipe } from './searchPipe';

describe('SearchServiceService', () => {
  let service: SearchPipe;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchPipe);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
