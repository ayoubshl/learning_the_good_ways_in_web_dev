import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFicheSalaireComponent } from './create-fiche-salaire.component';

describe('CreateFicheSalaireComponent', () => {
  let component: CreateFicheSalaireComponent;
  let fixture: ComponentFixture<CreateFicheSalaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateFicheSalaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateFicheSalaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
