import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalConsultationDetailComponent } from './medical-consultation-detail.component';

describe('MedicalConsultationDetailComponent', () => {
  let component: MedicalConsultationDetailComponent;
  let fixture: ComponentFixture<MedicalConsultationDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicalConsultationDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MedicalConsultationDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
