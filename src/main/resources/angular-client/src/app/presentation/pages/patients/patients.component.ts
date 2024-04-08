import {Component, OnInit} from '@angular/core';
import {DatePipe} from "@angular/common";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Patient} from "@domain/models/patient";
import {PatientRepository} from "@domain/interfaces/patient-repository";
import {PatientService} from "@app/infrastructure/services/patient.service";
import {DoctorRepository} from "@domain/interfaces/doctor-repository";
import {DoctorService} from "@app/infrastructure/services/doctor.service";

@Component({
    selector: 'app-patient',
    standalone: true,
    imports: [
        DatePipe,
        RouterLink
    ],
    templateUrl: './patients.component.html',
    styleUrl: './patients.component.scss',
    providers: [
        {provide: PatientRepository, useClass: PatientService},
        {provide: DoctorRepository, useClass: DoctorService}
    ],
})
export default class PatientsComponent implements OnInit {
    private doctorId: number = 0;
    public patients: Patient[] = [];

    constructor(private route: ActivatedRoute,
                private patientService: PatientRepository,
                private doctorService: DoctorRepository) {
        this.route.params.subscribe(params => {
            this.doctorId = params['doctorId'];
        });
    }

    ngOnInit(): void {
        console.debug("PatientsComponent.ngOnInit");
        this.loadPatients();
    }

    loadPatients(): void {
        if (this.doctorId) {
            this.doctorService.getPatients(this.doctorId).subscribe({
                next: (data) => {
                    this.patients = data;
                },
                error: (e) => console.error(e)
            });
        } else {
            this.patientService.getAll().subscribe({
                next: (data) => {
                    this.patients = data;
                },
                error: (e) => console.error(e)
            });
        }
    }
}
