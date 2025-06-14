import { TestBed } from '@angular/core/testing';

import { FicheSalaireService } from './fiche-salaire.service';

describe('FicheSalaireService', () => {
  let service: FicheSalaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheSalaireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
