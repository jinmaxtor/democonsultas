import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalConsultationComponent } from './medical-consultation.component';

describe('MedicalConsultationComponent', () => {
  let component: MedicalConsultationComponent;
  let fixture: ComponentFixture<MedicalConsultationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicalConsultationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MedicalConsultationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
