import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalConsultationsComponent } from './medical-consultations.component';

describe('MedicalConsultationsComponent', () => {
  let component: MedicalConsultationsComponent;
  let fixture: ComponentFixture<MedicalConsultationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicalConsultationsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MedicalConsultationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
