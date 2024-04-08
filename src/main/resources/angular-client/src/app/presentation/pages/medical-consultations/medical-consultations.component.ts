import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {DatePipe} from "@angular/common";
import {DoctorRepository} from "@domain/interfaces/doctor-repository";
import {DoctorService} from "@app/infrastructure/services/doctor.service";
import {MedicalConsultation} from "@domain/models/medical-consultation";
import {PatientRepository} from "@domain/interfaces/patient-repository";
import {PatientService} from "@app/infrastructure/services/patient.service";

@Component({
    selector: 'app-medical-consultations',
    standalone: true,
    imports: [
        DatePipe,
        RouterLink
    ],
    templateUrl: './medical-consultations.component.html',
    styleUrl: './medical-consultations.component.scss',
    providers: [
        {provide: DoctorRepository, useClass: DoctorService},
        {provide: PatientRepository, useClass: PatientService}
    ],
})
export default class MedicalConsultationsComponent implements OnInit{
    private id: number = 0;
    private doctorId: number = 0;
    private patientId: number = 0;

    public medicalConsultations: MedicalConsultation[] = [];

    constructor(private route: ActivatedRoute,
                private doctorService: DoctorRepository,
                private patientService: PatientRepository) {
        this.route.params.subscribe(params => {
            this.id = params['id'];
            this.doctorId = params['doctorId'];
            this.patientId = params['patientId'];
        });
    }

    ngOnInit(): void {
        this.loadMedicalConsultations();
    }

    loadMedicalConsultations(): void {
        if (this.doctorId) {
            this.doctorService.getMedicalConsultations(this.doctorId).subscribe({
                next: (data) => {
                    this.medicalConsultations = data;
                },
                error: (e) => console.error(e)
            });
        }

        if (this.patientId) {
            this.patientService.getMedicalConsultations(this.patientId).subscribe({
                next: (data) => {
                    this.medicalConsultations = data;
                },
                error: (e) => console.error(e)
            });
        }
    }
}
