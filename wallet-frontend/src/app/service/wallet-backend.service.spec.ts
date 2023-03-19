import { TestBed } from '@angular/core/testing';

import { WalletBackendService } from './wallet-backend.service';

describe('WalletBackendService', () => {
  let service: WalletBackendService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WalletBackendService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
