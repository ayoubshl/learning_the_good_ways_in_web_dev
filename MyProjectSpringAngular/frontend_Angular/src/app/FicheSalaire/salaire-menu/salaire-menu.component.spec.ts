import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalaireMenuComponent } from './salaire-menu.component';

describe('SalaireMenuComponent', () => {
  let component: SalaireMenuComponent;
  let fixture: ComponentFixture<SalaireMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SalaireMenuComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SalaireMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
