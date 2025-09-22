import { TestBed } from '@angular/core/testing';

import { AuthForward } from './auth-forward';

describe('AuthForward', () => {
  let service: AuthForward;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthForward);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
